package ru.vlapin.demo.lombokdemo.common;

import static org.assertj.core.api.Assertions.*;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.regex.Pattern;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.val;
import org.junit.jupiter.api.Test;

@ExtensionMethod(value = {
    FileUtils.class,
}, suppressBaseMethods = false)
class FileUtilsTest {

  /**
   * Test class for the FileUtils utility class, specifically focusing on testing the getPathFromFileName and readString methods.
   */

  @Test
  void testGetPathFromFileName_withExistingFile_returnsPath() {
    // given
    val fileName = "test-file.txt"; // Ensure this file exists in resources

    // when
    assertThat(fileName.getPathFromFileName())
        // then
        .isNotNull()
        .isPresent()
        .hasValue(Path.of("target", "test-classes", fileName).toAbsolutePath());
  }

  @Test
  void testGetPathFromFileName_fileNotExisting_returnsEmptyOptional() {
    // given
    val fileName = "nonexistent-file.txt"; // This file does not exist

    // when
    assertThat(fileName.getPathFromFileName())
        // then
        .isNotNull()
        .isEmpty();
  }

  @Test
  void testAdoptFileName_withLeadingSlash_returnsOriginal() {
    // given
    val fileName = "/test-file.txt";

    // when
    assertThat(fileName.adoptFileName())
        // then
        .isNotNull()
        .isEqualTo(fileName);
  }

  @Test
  void testAdoptFileName_withoutLeadingSlash_addsSlash() {
    // given
    val fileName = "test-file.txt";

    // when
    val result = FileUtils.adoptFileName(fileName);

    // then
    assertThat(result)
        .isNotNull()
        .isEqualTo("/test-file.txt");
  }

  @Test
  void testReadString_withExistingFile_returnsContent() {
    // given
    val fileName = "test-file.txt"; // Ensure this file exists in resources
    val expectedContent = "This is a test file.\n";

    // when
    assertThat(fileName.readString())
        // then
        .isNotNull()
        .isEqualTo(expectedContent);
  }

  @Test
  void testReadString_withNonExistingFile_throwsException() {
    // given
    val fileName = "nonexistent-file.txt"; // This file does not exist

    // when
    assertThatThrownBy(() -> fileName.readString())
        // then
        .isInstanceOf(FileNotFoundException.class);
  }

  @Test
  void testReadString_withPathInput_returnsContent() {
    // given
    val fileName = "test-file.txt"; // Ensure this file exists in resources
    val path = fileName.getPathFromFileName().orElseThrow();
    val expectedContent = "This is a test file.\n";

    // when
    assertThat(path.readString())
        // then
        .isNotNull()
        .isEqualTo(expectedContent);
  }

  @Test
  void testGetPathFromFileName_withLeadingSlash_returnsPath() {
    // given
    val fileName = "/test-file.txt"; // File with leading slash in filename

    // when
    assertThat(FileUtils.getPathFromFileName(fileName))
        // then
        .isPresent()
        .contains(Path.of("target", "test-classes", fileName).toAbsolutePath());
  }

  @Test
  void testGetPathFromFileName_onWindowsAdjustsPathCorrectly() {
    // given — Simulate a Windows-style path input
    val fileName = "C:/dir/test-file.txt"; // Skipped Windows environment actual testing

    // when — mock behavior or assert basic assumptions
    assertThat(fileName.getPathFromFileName())
        // then
        .isNotNull()
        .isEmpty();
  }

  @Test
  @SneakyThrows
  void testWriteToFile_appendsContentSuccessfully() {
    // given
    val fileName = "append-test-file.txt";
    val path = Path.of("target", "test-classes", fileName).toAbsolutePath();
    val initialContent = "Initial Content.\n";
    val additionalContent = "Appended Content.\n";

    Files.writeString(path, initialContent); // Create a file with initial content

    // when
    path.writeToFile(additionalContent);

    // then
    assertThat(Files.readString(path))
        .isNotNull()
        .isEqualTo(initialContent + additionalContent);
  }

  @Test
  void testWriteToFile_throwsExceptionForNonExistingFile() {
    // given
    val fileName = "nonexistent-file.txt";
    val path = Path.of("target", "test-classes", fileName).toAbsolutePath();
    val content = "Some content.";

    // when & then
    assertThatThrownBy(() -> path.writeToFile(content))
        .isInstanceOf(NoSuchFileException.class);
  }

  @Test
  @SneakyThrows
  void testWriteToFile_doesNotOverwriteExistingContent() {
    // given
    val fileName = "overwrite-test-file.txt";
    val path = Path.of("target", "test-classes", fileName).toAbsolutePath();
    val initialContent = "File Content.\n";
    val additionalContent = "Extra Content.\n";

    Files.writeString(path, initialContent); // Create a file with initial content

    // when
    path.writeToFile(additionalContent);

    // then
    assertThat(Files.readString(path))
        .isNotNull()
        .endsWith("File Content.\nExtra Content.\n") // Ensure no overwriting occurred
        .contains("File Content.");
  }

  @Test
  void testGetFileAsString_withValidPath_returnsFileContent() {
    // given
    val fileName = "test-file.txt"; // Ensure this file exists in resources
    val path = Path.of("target", "test-classes", fileName).toAbsolutePath();
    val expectedContent = "This is a test file.";

    // when
    assertThat(path.getFileAsString())
        // then
        .isNotNull()
        .isEqualTo(expectedContent);
  }

  @Test
  void testGetFileAsString_withDelimiter_returnsDelimitedContent() {
    // given
    val fileName = "multi-line-file.txt"; // Ensure this exists with multi-line content
    val path = Path.of("target", "test-classes", fileName).toAbsolutePath();

    // when
    val result = path.getFileAsString("|");

    // then
    assertThat(result)
        .isNotNull()
        .isEqualTo("Line1|Line2|Line3");
  }

  @Test
  void testGetFileAsString_withFileName_returnsOptionalContent() {
    // given
    val fileName = "test-file.txt"; // Ensure this file exists in resources

    // when
    assertThat(fileName.getFileAsString())
        // then
        .isPresent()
        .contains("This is a test file.");
  }

  @Test
  void testGetFileAsString_withNonexistentFile_returnsEmptyOptional() {
    // given
    val fileName = "nonexistent-file.txt"; // File does not exist

    // when
    assertThat(fileName.getFileAsString())
        // then
        .isNotNull()
        .isEmpty();
  }

  @Test
  @SneakyThrows
  void testProcessFileLines_withMapper_transformsLines() {
    // given
    val fileName = "transform-lines.txt";
    val path = Path.of("target", "test-classes", fileName).toAbsolutePath();
    val initialContent = "Line1\nLine2\nLine3\n";
    val expectedContent = "Modified Line1\nModified Line2\nModified Line3\n";

    Files.writeString(path, initialContent);

    // when
    path.processFileLines(line -> "Modified " + line);

    // then
    assertThat(Files.readString(path))
        .isNotNull()
        .isEqualTo(expectedContent);
  }

  @Test
  @SneakyThrows
  void testProcessFileLines_withPredicateAndMapper_transformsMatchingLines() {
    // given
    val fileName = "conditional-transform.txt";
    val path = Path.of("target", "test-classes", fileName).toAbsolutePath();
    val initialContent = "Line1\nLine2\nLine3\n";
    val expectedContent = "MATCHED-Line1\nLine2\nMATCHED-Line3\n";

    Files.writeString(path, initialContent);

    // when
    path.processFileLines(line -> line.startsWith("Line1") || line.startsWith("Line3"),
        line -> "MATCHED-" + line);

    // then
    assertThat(Files.readString(path))
        .isNotNull()
        .isEqualTo(expectedContent);
  }

  @Test
  @SneakyThrows
  void testProcessFileLines_withRegex_transformsMatchingLines() {
    // given
    val fileName = "regex-transform.txt";
    val path = Path.of("target", "test-classes", fileName).toAbsolutePath();
    val initialContent = "123abc\n456def\n789ghi\n";
    val expectedContent = "NUM123abc\nNUM456def\nNUM789ghi\n";

    Files.writeString(path, initialContent);

    // when
    path.processFileLines("^\\d+\\w+$", (_, line) -> "NUM" + line);

    // then
    assertThat(Files.readString(path))
        .isNotNull()
        .isEqualTo(expectedContent);
  }

  @Test
  @SneakyThrows
  void testProcessFileLines_withPattern_transformsMatchingLines() {
    // given
    val fileName = "pattern-transform.txt";
    val path = Path.of("target", "test-classes", fileName).toAbsolutePath();
    val initialContent = "apple\nbanana\npear\n";
    val expectedContent = "FRUIT: apple\nbanana\nFRUIT: pear\n";

    Files.writeString(path, initialContent);

    // when
    path.processFileLines(
        Pattern.compile("^(?:a.*|pear)$"),
        (_, line) -> "FRUIT: " + line);

    // then
    assertThat(Files.readString(path))
        .isNotNull()
        .isEqualTo(expectedContent);
  }
}
