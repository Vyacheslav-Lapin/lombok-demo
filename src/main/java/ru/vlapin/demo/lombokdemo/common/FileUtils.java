package ru.vlapin.demo.lombokdemo.common;

import static java.nio.file.StandardOpenOption.*;

import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.Contract;

@UtilityClass
@ExtensionMethod(value = Files.class,
                 suppressBaseMethods = false)
public class FileUtils {

  @SneakyThrows
  @Contract(pure = true)
  public Optional<Path> getPathFromFileName(String fileName) {
    return Optional.ofNullable(
            FileUtils.class.getResource(adoptFileName(fileName)))
               .map(URL::getFile)
               .map(s -> s.charAt(2) == ':' ? s.substring(1) : s) // for windows usage only - there we have addresses like "/c:/dir/1.md" and need to cut first char for correct work
               .map(Paths::get);
  }

  @SneakyThrows
  public String readString(Path path) {
    return path.readString();
  }

  @SneakyThrows
  public String readString(String filePath) {
    return getPathFromFileName(filePath)
               .orElseThrow(FileNotFoundException::new)
               .readString();
  }

  @SneakyThrows
  public Path writeToFile(Path filePath, String additionalContent) {
    return filePath.writeString(additionalContent, APPEND);
  }

  public String adoptFileName(String fileName) {
    return fileName.startsWith("/") ? fileName : "/%s".formatted(fileName);
  }

  @SneakyThrows
  public String getFileAsString(Path file) {
    @Cleanup val stringStream = file.lines();
    return stringStream.collect(Collectors.joining());
  }

  @SneakyThrows
  public String getFileAsString(Path file, String delimiter) {
    @Cleanup val stringStream = file.lines();
    return stringStream.collect(Collectors.joining(delimiter));
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
