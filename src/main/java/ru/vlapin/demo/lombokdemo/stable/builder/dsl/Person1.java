package ru.vlapin.demo.lombokdemo.stable.builder.dsl;

import java.util.function.UnaryOperator;
import lombok.Builder;
import lombok.experimental.Tolerate;

@Builder
public record Person1(String firstName,
                      String lastName,
                      int age,
                      boolean isMarried,
                      boolean isMale) {

  @Tolerate
  @SuppressWarnings("LombokBuilder")
  public static Person1 builder(UnaryOperator<Person1.Person1Builder> config) {
    return config.apply(new Person1.Person1Builder()).build();
  }
}
