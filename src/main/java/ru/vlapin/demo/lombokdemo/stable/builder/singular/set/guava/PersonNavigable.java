package ru.vlapin.demo.lombokdemo.stable.builder.singular.set.guava;

import java.util.NavigableSet;
import lombok.Builder;
import lombok.Singular;

@Builder
public record PersonNavigable(
  @Singular NavigableSet<String> contacts) {
}
