package ru.vlapin.demo.lombokdemo.common;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import lombok.experimental.ExtensionMethod;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * Unit tests for {@link CharSequenceUtil} class.
 */
@ExtensionMethod(value = CharSequenceUtil.class, suppressBaseMethods = false)
@DisplayNameGeneration(ReplaceCamelCase.class)
class CharSequenceUtilTest {

  @Test
  @DisplayName("capitalize method converts first character to uppercase")
  void capitalizeConvertsFirstCharacterToUppercase() {
    // given,when
    assertThat("hello world".capitalize())
        // then
        .isNotNull()
        .isEqualTo("Hello world");
  }

  @Test
  @DisplayName("countDuplicateCharacters handles text with alphanumeric and repeating characters")
  void countDuplicateCharactersHandlesTextWithRepeats() {
    // given, when
    assertThat("aabbcc123".countDuplicateCharacters())
        // then
        .isNotNull()
        .hasSize(6)
        .containsEntry('a', 2)
        .containsEntry('b', 2)
        .containsEntry('c', 2)
        .containsEntry('1', 1)
        .containsEntry('2', 1)
        .containsEntry('3', 1);
  }

  @Test
  @DisplayName("countDuplicateCharacters handles empty input string gracefully")
  void countDuplicateCharactersHandlesEmptyInput() {
    // given, when
    assertThat("".countDuplicateCharacters())
        // then
        .isNotNull()
        .isEmpty();
  }

  @Test
  @DisplayName("countDuplicateCharacters distinguishes between uppercase and lowercase characters")
  void countDuplicateCharactersDistinguishesCase() {
    // given, when
    assertThat("aAaA".countDuplicateCharacters())
        // then
        .isNotNull()
        .hasSize(2)
        .containsEntry('a', 2)
        .containsEntry('A', 2);
  }

  @Test
  @DisplayName("countDuplicateCharacters returns correct counts for unique characters")
  void countDuplicateCharactersHandlesUniqueCharacters() {
    // given, when
    assertThat("abc".countDuplicateCharacters())
        // then
        .isNotNull()
        .hasSize(3)
        .containsEntry('a', 1)
        .containsEntry('b', 1)
        .containsEntry('c', 1);
  }

  @Test
  @DisplayName("countDuplicateCharacters handles input with special characters")
  void countDuplicateCharactersSpecialCharacters() {
    // given, when
    assertThat("!@#$!@".countDuplicateCharacters())
        // then
        .isNotNull()
        .hasSize(4)
        .containsEntry('!', 2)
        .containsEntry('@', 2)
        .containsEntry('#', 1)
        .containsEntry('$', 1);
  }

  @Test
  @DisplayName("capitalize method handles single character input")
  void capitalizeHandlesSingleCharacterInput() {
    // given, when
    assertThat("h".capitalize())
        // then
        .isNotNull()
        .isEqualTo("H");
  }

  @Test
  @DisplayName("capitalize method throws exception for empty input")
  void capitalizeThrowsExceptionForEmptyInput() {
    // given, when, then
    assertThrows(StringIndexOutOfBoundsException.class, () -> "".capitalize());
  }

  @Test
  @DisplayName("capitalize method handles already capitalized input")
  void capitalizeHandlesAlreadyCapitalizedInput() {
    // given
    val input = "Hello World";

    // when
    assertThat(input.capitalize())
        // then
        .isNotNull()
        .isEqualTo(input);
  }

  @Test
  @DisplayName("capitalize method handles numeric input as first character")
  void capitalizeHandlesNumericInput() {
    // given
    val input = "123abc";

    // when
    assertThat(input.capitalize())
        // then
        .isNotNull()
        .isEqualTo(input);
  }

  @Test
  @DisplayName("capitalize method handles special character as first character")
  void capitalizeHandlesSpecialCharacter() {
    // given
    val input = "!hello";

    // when
    assertThat(input.capitalize())
        // then
        .isNotNull()
        .isEqualTo(input);
  }

  @Test
  @DisplayName("countDuplicateCharactersJava8 handles text with alphanumeric and repeating characters")
  void countDuplicateCharactersJava8HandlesTextWithRepeats() {
    // given, when
    assertThat("aabbcc123".countDuplicateCharactersJava8())
        // then
        .isNotNull()
        .hasSize(6)
        .containsEntry('a', 2L)
        .containsEntry('b', 2L)
        .containsEntry('c', 2L)
        .containsEntry('1', 1L)
        .containsEntry('2', 1L)
        .containsEntry('3', 1L);
  }

  @Test
  @DisplayName("countDuplicateCharactersJava8 handles empty input string gracefully")
  void countDuplicateCharactersJava8HandlesEmptyInput() {
    // given, when
    assertThat("".countDuplicateCharactersJava8())
        // then
        .isNotNull()
        .isEmpty();
  }

  @Test
  @DisplayName("countDuplicateCharactersJava8 distinguishes between uppercase and lowercase characters")
  void countDuplicateCharactersJava8DistinguishesCase() {
    // given, when
    assertThat("aAaA".countDuplicateCharactersJava8())
        // then
        .isNotNull()
        .hasSize(2)
        .containsEntry('a', 2L)
        .containsEntry('A', 2L);
  }

  @Test
  @DisplayName("countDuplicateCharactersJava8 returns correct counts for unique characters")
  void countDuplicateCharactersJava8HandlesUniqueCharacters() {
    // given, when
    assertThat("abc".countDuplicateCharactersJava8())
        // then
        .isNotNull()
        .hasSize(3)
        .containsEntry('a', 1L)
        .containsEntry('b', 1L)
        .containsEntry('c', 1L);
  }

  @Test
  @DisplayName("countDuplicateCharactersJava8 handles input with special characters")
  void countDuplicateCharactersJava8SpecialCharacters() {
    // given, when
    assertThat("!@#$!@".countDuplicateCharactersJava8())
        // then
        .isNotNull()
        .hasSize(4)
        .containsEntry('!', 2L)
        .containsEntry('@', 2L)
        .containsEntry('#', 1L)
        .containsEntry('$', 1L);
  }

  @Test
  @DisplayName("getCharCount handles text with alphanumeric and repeating characters")
  void getCharCountTextWithRepeats() {
    // given, when
    assertThat("aabbcc123".getCharCount())
        // then
        .isNotNull()
        .hasSize(6)
        .containsEntry('a', 2)
        .containsEntry('b', 2)
        .containsEntry('c', 2)
        .containsEntry('1', 1)
        .containsEntry('2', 1)
        .containsEntry('3', 1);
  }

  @Test
  @DisplayName("getCharCount handles empty input string gracefully")
  void getCharCountEmptyInput() {
    // given, when
    assertThat("".getCharCount())
        // then
        .isNotNull()
        .isEmpty();
  }

  @Test
  @DisplayName("getCharCount distinguishes between uppercase and lowercase characters")
  void getCharCountDistinguishesCase() {
    // given, when
    assertThat("aAaA".getCharCount())
        // then
        .isNotNull()
        .hasSize(2)
        .containsEntry('a', 2)
        .containsEntry('A', 2);
  }

  @Test
  @DisplayName("getCharCount returns correct counts for unique characters")
  void getCharCountUniqueCharacters() {
    // given, when
    assertThat("abc".getCharCount())
        // then
        .isNotNull()
        .hasSize(3)
        .containsEntry('a', 1)
        .containsEntry('b', 1)
        .containsEntry('c', 1);
  }

  @Test
  @DisplayName("getCharCount handles input with special characters")
  void getCharCountSpecialCharacters() {
    // given, when
    assertThat("!@#$!@".getCharCount())
        // then
        .isNotNull()
        .hasSize(4)
        .containsEntry('!', 2)
        .containsEntry('@', 2)
        .containsEntry('#', 1)
        .containsEntry('$', 1);
  }
}
