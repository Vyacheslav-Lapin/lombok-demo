package ru.vlapin.demo.lombokdemo.common;

import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Utility class providing helper methods for working with {@link BiPredicate}.
 */
@UtilityClass
@SuppressWarnings("unused")
public class BiPredicateUtils {

  /**
   * Creates a {@link Predicate} that evaluates a {@link Map.Entry} based on the given {@link BiPredicate}.
   *
   * @param <K> the type of keys in the map entry
   * @param <V> the type of values in the map entry
   * @param test a {@link BiPredicate} used to test the key and value of a map entry
   * @return a {@link Predicate} that evaluates map entries using the specified {@link BiPredicate}
   */
  public <K, V> Predicate<Map.Entry<K, V>> p(BiPredicate<? super K, ? super V> test) {
    return entry -> test.test(entry.getKey(), entry.getValue());
  }
}
