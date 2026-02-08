package ru.vlapin.demo.lombokdemo.common;

import io.vavr.CheckedFunction1;
import java.sql.ResultSet;
import java.util.Iterator;
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
   * Converts a {@link ResultSet} into a sequential {@link Stream} of type {@code T}, using the provided
   * mapping function to transform each row in the {@code ResultSet}.
   *
   * @param resultSet The {@link ResultSet} to be converted into a {@link Stream}.
   *                  Each row of the {@code ResultSet} will be processed to generate the stream elements.
   * @param mapper    The mapping function that converts a row from the {@code ResultSet}
   *                  into an element of type {@code T}.
   * @param <T>       The type of the elements returned by the {@link Stream}.
   * @return A sequential {@link Stream} of {@code T}, where each element corresponds to a row
   *         in the {@code ResultSet} transformed by the {@code mapper}.
   *
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

  /**
   * Converts a {@link ResultSet} into an {@link Iterator} of type {@code T}, using the provided
   * mapping function to transform each row in the {@code ResultSet}.
   *
   * @param resultSet The {@link ResultSet} to be transformed into an {@link Iterator}.
   *                  Each row of the {@code ResultSet} will be processed to generate the iterator elements.
   * @param mapper    The mapping function that converts a row from the {@code ResultSet}
   *                  into an element of type {@code T}.
   * @param <T>       The type of the elements returned by the {@link Iterator}.
   * @return An {@link Iterator} of type {@code T}, where each element corresponds to a row
   *         in the {@code ResultSet} transformed by the {@code mapper}.
   */
  public <T> Iterator<T> toIterator(ResultSet resultSet,
                                    CheckedFunction1<? super ResultSet, ? extends T> mapper) {
    // Returns an iterator that maps and advances a result set
    return new Iterator<>() {
      @Override
      @SneakyThrows
      public boolean hasNext() {
        return !resultSet.isLast();
      }

      @Override
      @SneakyThrows
      public T next() {
        if (!resultSet.next())
          throw new RuntimeException();
        return mapper.apply(resultSet);
      }
    };
  }
}
