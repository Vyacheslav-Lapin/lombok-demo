package ru.vlapin.demo.lombokdemo.common;

import static org.assertj.core.api.Assertions.*;

import java.util.AbstractMap;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * Test class for {@link BiPredicateUtils}.
 * <p>
 * This test verifies the behavior of the {@link BiPredicateUtils#p(BiPredicate)} method, which creates a {@link Predicate} that evaluates a
 * {@link Map.Entry} based on a provided {@link BiPredicate}.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class BiPredicateUtilsTest {

  @Test
  @DisplayName("Predicate evaluates successfully for matching entry")
  void predicateEvaluatesSuccessfullyForMatchingEntryTest() {
    // given
    BiPredicate<Integer, String> biPredicate = (key, value) -> key > 10 && value.startsWith("L");
    Predicate<Map.Entry<Integer, String>> predicate = BiPredicateUtils.p(biPredicate);

    Map.Entry<Integer, String> entry = new AbstractMap.SimpleEntry<>(20, "Lombok");

    // when
    boolean result = predicate.test(entry);

    // then
    assertThat(result).isTrue();
  }

  @Test
  @DisplayName("Predicate evaluates to false for non-matching entry")
  void predicateEvaluatesToFalseForNonMatchingEntryTest() {
    // given
    BiPredicate<Integer, String> biPredicate = (key, value) -> key > 10 && value.startsWith("L");
    Predicate<Map.Entry<Integer, String>> predicate = BiPredicateUtils.p(biPredicate);

    Map.Entry<Integer, String> entry = new AbstractMap.SimpleEntry<>(5, "Lombok");

    // when
    boolean result = predicate.test(entry);

    // then
    assertThat(result).isFalse();
  }

  @Test
  @DisplayName("Predicate handles null key and value correctly")
  void predicateHandlesNullKeyAndValueCorrectlyTest() {
    // given
    BiPredicate<Object, Object> biPredicate = (key, value) -> key == null && value == null;
    Predicate<Map.Entry<Object, Object>> predicate = BiPredicateUtils.p(biPredicate);

    Map.Entry<Object, Object> entry = new AbstractMap.SimpleEntry<>(null, null);

    // when
    boolean result = predicate.test(entry);

    // then
    assertThat(result).isTrue();
  }

  @Test
  @DisplayName("Predicate evaluates logic with complex entry values")
  void predicateEvaluatesLogicWithComplexEntryValuesTest() {
    // given
    BiPredicate<String, Integer> biPredicate = (key, value) -> key.length() > 5 && value % 2 == 0;
    Predicate<Map.Entry<String, Integer>> predicate = BiPredicateUtils.p(biPredicate);

    Map.Entry<String, Integer> entry = new AbstractMap.SimpleEntry<>("LombokDemo", 42);

    // when
    boolean result = predicate.test(entry);

    // then
    assertThat(result).isTrue();
  }
}
