package ru.vlapin.demo.lombokdemo.stable.builder;

import java.util.stream.IntStream;

import lombok.Builder;

@Builder
public class BuilderDefaultExpressionDemo {
  @Builder.Default double[] d =
      IntStream.range(0, 1_000_000)
          .mapToDouble(Math::atan)
          .peek(System.out::println)
          .toArray();
}
