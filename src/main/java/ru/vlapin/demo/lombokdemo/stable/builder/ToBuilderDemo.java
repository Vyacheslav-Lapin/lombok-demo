package ru.vlapin.demo.lombokdemo.stable.builder;

import lombok.Builder;

@Builder//(toBuilder = true)
public class ToBuilderDemo {
  int x;
  String s;
  boolean b;

  public ToBuilderDemoBuilder toBuilder() {
    return new ToBuilderDemoBuilder()
        .x(x)
        .s(s)
        .b(b);
  }
}
