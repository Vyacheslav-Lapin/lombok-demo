package ru.vlapin.demo.lombokdemo.common;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.*;

@UtilityClass
@ExtensionMethod({
    Files.class,
})
public class FileUtils {

  @SneakyThrows
  @Contract(pure = true)
  public @NotNull Optional<Path> getPathFromFileName(@NotNull String fileName) {
    return Optional.ofNullable(
            FileUtils.class.getResource(adoptFileName(fileName)))
               .map(URL::getFile)
               .map(s -> s.charAt(2) == ':' ? s.substring(1)
                             : s) // for windows usage only - there we have addresses like "/c:/asdf/sdf" and need to cut first char for correct work
               .map(Paths::get);
  }

  @SneakyThrows
  public String readString(@NotNull Path path) {
    return path.readString();
  }

  @SneakyThrows
  public String readString(String filePath) {
    return getPathFromFileName(filePath)
               .orElseThrow(FileNotFoundException::new)
               .readString();
  }

  @SneakyThrows
  public Path writeToFile(@NotNull Path filePath, String additionalContent) {
    return filePath.writeString(additionalContent, APPEND);
  }

  public String adoptFileName(String fileName) {
    return fileName.startsWith("/") ? fileName : "/" + fileName;
  }

  @SneakyThrows
  public String getFileAsString(@NotNull Path file) {
    @Cleanup Stream<String> stringStream = file.lines();
    return stringStream.collect(Collectors.joining());
  }

  @SneakyThrows
  public String getFileAsString(@NotNull Path file, String delimeter) {
    @Cleanup Stream<String> stringStream = file.lines();
    return stringStream.collect(Collectors.joining(delimeter));
  }

  @SneakyThrows
  public Optional<String> getFileAsString(String folder, String fileName) {
    return getPathFromFileName("%s/%s".formatted(folder, fileName))
               .map(FileUtils::getFileAsString);
  }

  @SneakyThrows
  public Optional<String> getFileAsString(String fileName) {
    return getPathFromFileName(fileName)
               .map(FileUtils::getFileAsString);
  }
}
