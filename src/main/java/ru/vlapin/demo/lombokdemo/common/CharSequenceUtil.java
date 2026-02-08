package ru.vlapin.demo.lombokdemo.common;

import static java.lang.Character.*;
import static java.util.function.Function.*;
import static java.util.stream.Collectors.*;

import java.util.HashMap;
import java.util.Map;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.Contract;

/**
 * Utility class providing various helper methods for working with {@link CharSequence}.
 * This class contains methods to transform, analyze, and manipulate character sequences.
 * Each method is designed to handle specific tasks related to character sequences.
 */
@UtilityClass
public class CharSequenceUtil {

  /**
   * Capitalizes the first character of the given {@code CharSequence}.
   * If the input is empty, the behavior may result in an error or unexpected output.
   *
   * @param s the input character sequence to be transformed, must not be null.
   * @return a {@code String} where the first character is capitalized, followed by the rest of the original sequence.
   */
  public String capitalize(CharSequence s) {
    return "%s%s".formatted(toUpperCase(s.charAt(0)), s.subSequence(1, s.length()));
  }

  /**
   * Converts a camelCase {@code CharSequence} into a space-separated, lower-case string.
   * Uppercase letters in the input are replaced with a space followed by their lower-case equivalent.
   *
   * @param camelCase the input camelCase {@code CharSequence} to be transformed, must not be null.
   * @return a {@code CharSequence} where camelCase formatting is replaced with space-separated lower-case words.
   */
  public CharSequence camelCaseToSpacedString(CharSequence camelCase) {
    // Converts camelCase input to space‑separated lowercase string
    return camelCase.chars()
        .mapMulti((c, consumer) -> {
          if (isUpperCase(c))
            consumer.accept(' ');
          consumer.accept(toLowerCase(c));
        })
        .mapToObj(c -> (char) c)
        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
  }

  /**
   * Counts the occurrences of each character in the given character sequence and
   * returns a map where keys are characters and values are their respective counts.
   *
   * @param s the input character sequence to be analyzed; must not be null.
   * @return a map containing each character from the input sequence as a key
   * and the number of its occurrences as the corresponding value.
   */
  public Map<Character, Integer> countDuplicateCharacters(CharSequence s) {
    val result = new HashMap<Character, Integer>();
    for (char c : s.toString().toCharArray())
      result.compute(c,
          (_, integer) -> integer == null ? 1 : integer + 1);
    return result;
  }

  /**
   * Counts the occurrences of each character in the given character sequence using Java 8 Streams
   * and returns a map where keys are characters and values are their respective counts.
   *
   * @param s the input character sequence to be analyzed; must not be null.
   * @return a map containing each character from the input sequence as a key
   * and the number of its occurrences as the corresponding value.
   */
  public Map<Character, Long> countDuplicateCharactersJava8(CharSequence s) {
    return s.chars()
        .mapToObj(character -> (char) character)
        .collect(groupingBy(identity(), counting()));
  }

  /**
   * Counts the occurrences of each character in the given character sequence
   * and returns a map where the keys are characters, and the values are their respective counts.
   *
   * @param chars the input character sequence to be analyzed; must not be null.
   * @return a map where keys are characters from the input sequence, and the values
   *         are the number of times each character appears in the sequence.
   */
  @Contract(pure = true)
  public Map<Character, Integer> getCharCount(CharSequence chars) {
    return chars.chars().collect(
        HashMap::new,
        (hashMap, value) -> hashMap.merge((char) value, 1, Integer::sum),
        HashMap::putAll);
  }
}
