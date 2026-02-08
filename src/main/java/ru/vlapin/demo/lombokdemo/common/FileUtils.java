package ru.vlapin.demo.lombokdemo.common;

import static java.nio.charset.StandardCharsets.*;
import static java.nio.file.StandardOpenOption.*;

import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.Contract;

/**
 * Utility class for file-related operations, providing convenient methods
 * for reading, writing, and processing file content. Supports resolving file
 * paths, handling both absolute and relative paths, and includes functionality
 * for Windows-specific file systems.
 */
@UtilityClass
@ExtensionMethod(value = Files.class,
                 suppressBaseMethods = false)
public class FileUtils {

  /**
   * Resolves a file path from the given file name, providing support for both
   * relative and absolute paths, including adjustments for Windows-style file paths.
   *
   * @param fileName the name of the file for which the path needs to be resolved.
   *                 If the file name does not start with "/", it will be prefixed
   *                 with "/" to generate a proper resource path.
   * @return an {@code Optional<Path>} containing the resolved file path
   *         if the file exists, or an empty {@code Optional} otherwise.
   */
  @SneakyThrows
  @Contract(pure = true)
  public Optional<Path> getPathFromFileName(String fileName) {
    return Optional.ofNullable(
            FileUtils.class.getResource(adoptFileName(fileName)))
               .map(URL::getFile)
               .map(s -> s.charAt(2) == ':' ? s.substring(1) : s) // for windows usage only - there we have addresses like "/c:/dir/1.md" and need to cut the first char for correct work
               .map(Paths::get);
  }

  /**
   * Reads the content of a file specified by the given {@link Path} and returns it as a string.
   *
   * @param path the file path to read from; must not be {@code null}.
   * @return the file content as a string.
   * @throws java.nio.file.NoSuchFileException if the file does not exist.
   * @throws java.nio.file.AccessDeniedException if access to the file is denied.
   * @throws java.io.IOException if an I/O error occurs.
   */
  @SneakyThrows
  @SuppressWarnings("JavadocDeclaration")
  public String readString(Path path) {
    return path.readString();
  }

  /**
   * Reads the content of a file located at the specified file path and returns it as a string.
   *
   * @param filePath the file path as a string. Must not be null or empty.
   * @return the content of the file as a string.
   * @throws FileNotFoundException if the file does not exist.
   * @throws java.io.IOException if an I/O error occurs while reading the file.
   */
  @SneakyThrows
  @SuppressWarnings("JavadocDeclaration")
  public String readString(String filePath) {
    return getPathFromFileName(filePath)
               .orElseThrow(FileNotFoundException::new)
               .readString();
  }

  /**
   * Appends the specified content to the given file at the specified path.
   *
   * @param filePath the {@code Path} of the file to which content will be written.
   *                 Must not be {@code null}.
   * @param additionalContent the content to append to the file.
   *                          Must not be {@code null} or empty.
   * @return the {@code Path} of the file after writing the content.
   * @throws java.nio.file.NoSuchFileException if the file does not exist.
   * @throws java.nio.file.AccessDeniedException if writing to the file is not permitted.
   * @throws java.io.IOException if an I/O error occurs while writing to the file.
   */
  @SneakyThrows
  @SuppressWarnings("JavadocDeclaration")
  public Path writeToFile(Path filePath, String additionalContent) {
    return filePath.writeString(additionalContent, APPEND);
  }

  /**
   * Adjusts the given file name to ensure it starts with a "/" character,
   * commonly required for resource paths. If the file name already starts
   * with "/", it is returned unmodified. Otherwise, "/" is prefixed to it.
   *
   * @param fileName the name of the file that needs to be adjusted. Must not be null.
   * @return the adjusted file name starting with a "/", or the original file name
   *         if it already starts with "/".
   */
  public String adoptFileName(String fileName) {
    return fileName.startsWith("/") ? fileName : "/%s".formatted(fileName);
  }

  /**
   * Reads the content of a file at the specified {@link Path} and returns it as a single string.
   * The method processes the file line by line and concatenates the lines with no delimiters in between.
   *
   * @param file the path to the file that needs to be read; must not be {@code null}.
   * @return the file content as a single string.
   * @throws java.nio.file.NoSuchFileException if the file does not exist.
   * @throws java.nio.file.AccessDeniedException if access to the file is denied.
   * @throws java.io.IOException if an I/O error occurs while reading the file.
   */
  @SneakyThrows
  @SuppressWarnings("JavadocDeclaration")
  public String getFileAsString(Path file) {
    @Cleanup val stringStream = file.lines();
    return stringStream.collect(Collectors.joining());
  }

  /**
   * Reads the contents of a file line by line from the specified {@link Path} and returns it as
   * a single string, with the lines joined using the provided delimiter.
   *
   * @param file the path of the file to be read; must not be {@code null}.
   * @param delimiter the string used to separate each line in the resulting output; must not be {@code null}.
   * @return the contents of the file as a single string with lines joined by the specified delimiter.
   * @throws java.nio.file.NoSuchFileException if the file does not exist.
   * @throws java.nio.file.AccessDeniedException if access to the file is denied.
   * @throws java.io.IOException if an I/O error occurs while reading the file.
   */
  @SneakyThrows
  @SuppressWarnings("JavadocDeclaration")
  public String getFileAsString(Path file, String delimiter) {
    @Cleanup val stringStream = file.lines();
    return stringStream.collect(Collectors.joining(delimiter));
  }

  /**
   * Retrieves the content of a file specified by its folder and file name, and returns it as an {@code Optional<String>}.
   * The method combines the folder and file name to create a file path,
   * resolves the path, and attempts to fetch the file content as a string.
   *
   * @param folder the folder containing the file. Must not be null or empty.
   * @param fileName the name of the file to be read. Must not be null or empty.
   * @return an {@code Optional<String>} containing the file content if the file path
   *         is resolved and the content is successfully read, or an empty {@code Optional} otherwise.
   */
  @SneakyThrows
  public Optional<String> getFileAsString(String folder, String fileName) {
    return getPathFromFileName("%s/%s".formatted(folder, fileName))
               .map(FileUtils::getFileAsString);
  }

  /**
   * Retrieves the content of a file specified by its name and returns it as an {@code Optional<String>}.
   * If the file path is resolved successfully and the file content is read without issues,
   * the content is wrapped in an {@code Optional}. Otherwise, an empty {@code Optional} is returned.
   *
   * @param fileName the name of the file whose content is to be retrieved.
   *                 If the file name does not start with a "/", it may be adjusted
   *                 to generate an appropriate resource path.
   * @return an {@code Optional<String>} containing the file content as a string
   *         if the file is found and read successfully, or an empty {@code Optional} otherwise.
   */
  @SneakyThrows
  public Optional<String> getFileAsString(String fileName) {
    return getPathFromFileName(fileName)
               .map(FileUtils::getFileAsString);
  }

  /**
   * Processes the lines of a file by applying a mapping function to each line.
   * The transformed lines are then written back to the same file.
   *
   * @param file the path of the file to be processed; must not be {@code null}.
   * @param mapper a function to transform each line of the file; must not be {@code null}.
   *               The function takes a line as input and returns the transformed line.
   * @throws java.nio.file.NoSuchFileException if the specified file does not exist.
   * @throws java.nio.file.AccessDeniedException if access to the file is denied.
   * @throws java.io.IOException if an I/O error occurs during file reading or writing.
   */
  @SneakyThrows
  @SuppressWarnings("JavadocDeclaration")
  public void processFileLines(Path file,
                               Function<? super String, String> mapper) {
    @Cleanup val lines = file.lines(UTF_8);
    file.write(lines.map(mapper).toList(), UTF_8);
  }

  /**
   * Processes the lines of a file based on a condition and applies a transformation
   * to the lines that meet the specified condition. The modified lines are written
   * back to the same file.
   *
   * @param file                the path to the file to be processed; must not be {@code null}.
   * @param isLineNeedToChange  a predicate to test whether a line needs to be changed;
   *                            must not be {@code null}. The predicate takes a line
   *                            as input and returns {@code true} if the line needs to
   *                            be transformed, and {@code false} otherwise.
   * @param mapper              a function that performs the transformation on the lines
   *                            that satisfy the condition; must not be {@code null}.
   *                            The function takes a line as input and returns the
   *                            transformed line.
   * @throws java.nio.file.NoSuchFileException if the specified file does not exist.
   * @throws java.nio.file.AccessDeniedException if access to the file is denied.
   * @throws java.io.IOException if an I/O error occurs during file reading or writing.
   */
  @SneakyThrows
  @SuppressWarnings("JavadocDeclaration")
  public void processFileLines(Path file,
                               Predicate<String> isLineNeedToChange,
                               Function<? super String, String> mapper) {
    processFileLines(file, line -> isLineNeedToChange.test(line) ? mapper.apply(line) : line);
  }

  /**
   * Processes each line of the given file by applying a regex pattern and a mapping function.
   *
   * @param file the path of the file to be processed
   * @param pattern a regular expression pattern to match lines in the file
   * @param mapper a function that takes a Matcher object and the current line,
   *               and returns the processed line as a string
   */
  @SneakyThrows
  public void processFileLines(Path file,
                               @Language("RegExp") String pattern,
                               BiFunction<? super Matcher, ? super String, String> mapper) {
    processFileLines(file, Pattern.compile(pattern), mapper);
  }

  /**
   * Processes the lines of a file, applying a mapping function to each line based on a pattern.
   *
   * @param file the path to the file to be processed
   * @param pattern the regular expression pattern to match against each line
   * @param mapper a function to transform lines that match the pattern; takes a Matcher for the pattern
   *        and the original line as arguments and returns the transformed line
   */
  @SneakyThrows
  public void processFileLines(Path file,
                               Pattern pattern,
                               BiFunction<? super Matcher, ? super String, String> mapper) {
    processFileLines(file, line -> {
      val matcher = pattern.matcher(line);
      return matcher.matches() ? mapper.apply(matcher, line) : line;
    });
  }
}
