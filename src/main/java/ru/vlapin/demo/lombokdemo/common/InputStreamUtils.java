package ru.vlapin.demo.lombokdemo.common;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction1;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;

import java.io.InputStream;
import java.util.Objects;

@UtilityClass
@ExtensionMethod({
    FileUtils.class,
    Objects.class,
})
public class InputStreamUtils {

  @SneakyThrows
  @SuppressWarnings("unused")
  public <T> T mapFileInputStream(String fileName, CheckedFunction1<? super InputStream, ? extends T> fisMapper) {

    @Cleanup val inputStream =
        InputStreamUtils.class.getResourceAsStream(
                fileName.adoptFileName())
            .requireNonNull();

    return fisMapper.apply(inputStream);
  }

  @SneakyThrows
  public void withFileInputStream(String fileName, CheckedConsumer<? super InputStream> fisConsumer) {
    @Cleanup val inputStream =
        InputStreamUtils.class.getResourceAsStream(
                fileName.adoptFileName())
            .requireNonNull();

    fisConsumer.accept(inputStream);
  }
}
