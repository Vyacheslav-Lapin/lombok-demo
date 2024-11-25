package ru.vlapin.demo.lombokdemo.stable.builder;

import lombok.Builder;

public class BuilderBeforeConstructorDemo {

  int x;
  String s;
  boolean b;

  @Builder
  public BuilderBeforeConstructorDemo(int x, String s, boolean b) {
    this.x = x;
    this.s = s;
    this.b = b;
  }
}
