package ru.vlapin.demo.lombokdemo.stable.builder.singular.set.guava;

import java.util.Set;
import lombok.Builder;
import lombok.Singular;

@Builder
public record PersonSingle(
  @Singular("contactEntry")
  Set<String> contact) {
}
