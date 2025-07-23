package ru.vlapin.demo.lombokdemo.common;

import io.vavr.CheckedFunction1;
import java.sql.ResultSet;
import java.util.Spliterator;
import java.util.Spliterators.AbstractSpliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JdbcUtils {

  /**
   * @see <a href="https://www.baeldung.com/stream-api-jdbc-resultset">Processing JDBC ResultSet With Stream API (by baeldung)</a>
   */
  public <T> Stream<T> toStream(ResultSet resultSet,
                                CheckedFunction1<? super ResultSet, ? extends T> mapper) {
    return StreamSupport.stream(
        new AbstractSpliterator<>(Long.MAX_VALUE, Spliterator.ORDERED) {
          @SneakyThrows
          public @Override boolean tryAdvance(Consumer<? super T> action) {
            if (!resultSet.next())
              return false;
            action.accept(mapper.apply(resultSet));
            return true;
          }
        },
        false);
  }
}
