package ru.vlapin.demo.lombokdemo.common;

import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

@UtilityClass
public class BiPredicateUtils {

  public <K, V> Predicate<Map.Entry<K, V>> p(BiPredicate<? super K, ? super V> test) {
    return entry -> test.test(entry.getKey(), entry.getValue());
  }
}
