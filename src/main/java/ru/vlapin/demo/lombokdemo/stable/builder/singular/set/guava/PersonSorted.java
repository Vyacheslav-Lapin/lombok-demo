package ru.vlapin.demo.lombokdemo.stable.builder.singular.set.guava;

import java.util.SortedSet;
import lombok.Builder;
import lombok.Singular;

@Builder
public record PersonSorted(
  @Singular SortedSet<String> contacts) {
}
