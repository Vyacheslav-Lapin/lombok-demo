package ru.vlapin.demo.lombokdemo.stable.builder.singular.set.guava;

import java.util.Set;
import lombok.Builder;
import lombok.Singular;

@Builder
public record PersonIgnoreNull(
  @Singular(ignoreNullCollections = true)
  Set<String> contacts) {
}
