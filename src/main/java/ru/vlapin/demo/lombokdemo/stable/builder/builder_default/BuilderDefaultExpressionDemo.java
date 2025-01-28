package ru.vlapin.demo.lombokdemo.stable.builder.builder_default;

import java.util.stream.IntStream;

import lombok.Builder;

@Builder
@SuppressWarnings({"java:S3864", "java:S106"})
public class BuilderDefaultExpressionDemo {
  @Builder.Default double[] d =
      IntStream.range(0, 1_000_000)
          .mapToDouble(Math::atan)
          .peek(System.out::println)
          .toArray();
}
