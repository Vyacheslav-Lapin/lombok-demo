package ru.vlapin.demo.lombokdemo.stable.builder.singular.map;

import java.util.Map;
import lombok.Builder;
import lombok.Singular;

@Builder
public record PersonSingle(
  @Singular("contactEntry")
  Map<String, String> contact) {
}
