package ru.vlapin.demo.lombokdemo.stable.builder.singular.map;

import java.util.NavigableMap;
import lombok.Builder;
import lombok.Singular;

@Builder
public record PersonNavigable(
  @Singular
  NavigableMap<String, Integer> contacts) {
}
