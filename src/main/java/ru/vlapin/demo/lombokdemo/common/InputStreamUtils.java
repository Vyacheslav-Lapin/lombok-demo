package ru.vlapin.demo.lombokdemo.common;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction1;
import java.io.InputStream;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;

@UtilityClass
@ExtensionMethod({
    FileUtils.class,
    Objects.class,
})
public class InputStreamUtils {

  @SuppressWarnings("unused")
  public <T> T mapFileInputStreamChecked(String fileName,
                                         CheckedFunction1<? super InputStream, ? extends T> fisMapper) {
    return mapFileInputStream(fileName, fisMapper.unchecked());
  }

  @SneakyThrows
  public <T> T mapFileInputStream(String fileName,
                                  Function<? super InputStream, ? extends T> fisMapper) {

    @Cleanup val inputStream =
        InputStreamUtils.class.getResourceAsStream(
                fileName.adoptFileName())
            .requireNonNull();

    return fisMapper.apply(inputStream);
  }

  public void withFileInputStreamChecked(String fileName,
                                         CheckedConsumer<? super InputStream> fisConsumer) {
    withFileInputStream(fileName, fisConsumer.unchecked());
  }

  @SneakyThrows
  public void withFileInputStream(String fileName,
                                  Consumer<? super InputStream> fisConsumer) {
    @Cleanup val inputStream =
        InputStreamUtils.class.getResourceAsStream(
                fileName.adoptFileName())
            .requireNonNull();

    fisConsumer.accept(inputStream);
  }
}
