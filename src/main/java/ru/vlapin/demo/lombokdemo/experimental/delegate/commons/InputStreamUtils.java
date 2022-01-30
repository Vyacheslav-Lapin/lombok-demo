package ru.vlapin.demo.lombokdemo.experimental.delegate.commons;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction1;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.NotNull;

@UtilityClass
@ExtensionMethod(InputStreamUtils.class)
public class InputStreamUtils {

  @SneakyThrows
  public <T> T mapFileInputStream(@NotNull String fileName,
                                  @NotNull CheckedFunction1<InputStream, T> fisMapper) {
    @Cleanup var inputStream =
        InputStreamUtils.class
            .getResourceAsStream("/%s".formatted(fileName));
    return fisMapper.apply(inputStream);
  }

  @SneakyThrows
  public void withFileInputStream(@NotNull String fileName,
                                  @NotNull CheckedConsumer<InputStream> fisConsumer) {
    if (!fileName.startsWith("/"))
      fileName = "/%s".formatted(fileName);
    @Cleanup val inputStream = InputStreamUtils.class.getResourceAsStream(fileName);
    fisConsumer.accept(inputStream);
  }

  @SneakyThrows
  public String getFileAsString(@NotNull Path file) {
    @Cleanup Stream<String> stringStream = Files.lines(file);
    return stringStream.collect(Collectors.joining());
  }

  public Optional<Path> getPath(@NotNull String fileName) {
    if (!fileName.startsWith("/"))
      fileName = "/%s".formatted(fileName);
    return Optional.ofNullable(InputStreamUtils.class.getResource(fileName))
               .map(URL::getFile)
               .map(Paths::get);
  }

  @SneakyThrows
  public Optional<String> getFileAsString(String folder, String fileName) {
    return getPath("%s/%s".formatted(folder, fileName))
               .map(InputStreamUtils::getFileAsString);
  }

  @SneakyThrows
  public Optional<String> getFileAsString(String fileName) {
    return getPath("/%s".formatted(fileName))
               .map(InputStreamUtils::getFileAsString);
  }
}
