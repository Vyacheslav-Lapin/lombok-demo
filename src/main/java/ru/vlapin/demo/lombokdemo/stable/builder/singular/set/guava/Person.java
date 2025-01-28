package ru.vlapin.demo.lombokdemo.stable.builder.singular.set.guava;

import java.util.Set;
import lombok.Builder;
import lombok.Singular;

@Builder
public record Person(
  @Singular Set<String> contacts) {
}
