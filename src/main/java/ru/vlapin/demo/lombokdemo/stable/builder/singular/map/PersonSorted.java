package ru.vlapin.demo.lombokdemo.stable.builder.singular.map;

import java.util.SortedMap;
import lombok.Builder;
import lombok.Singular;

@Builder
public record PersonSorted(
  @Singular
  SortedMap<String, Integer> contacts) {
}
