package ru.vlapin.demo.lombokdemo.common;

import java.io.InputStream;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction1;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.NotNull;

@UtilityClass
@ExtensionMethod({
    FileUtils.class,
})
public class InputStreamUtils {

  @SneakyThrows
  public <T> T mapFileInputStream(@NotNull String fileName,
                                  @NotNull CheckedFunction1<InputStream, T> fisMapper) {

    @Cleanup
    val inputStream = InputStreamUtils.class.getResourceAsStream(fileName.adoptFileName());
    return fisMapper.apply(inputStream);
  }

  @SneakyThrows
  public void withFileInputStream(@NotNull String fileName,
                                  @NotNull CheckedConsumer<? super InputStream> fisConsumer) {
    @Cleanup
    val inputStream = InputStreamUtils.class.getResourceAsStream(fileName.adoptFileName());
    fisConsumer.accept(inputStream);
  }

}
