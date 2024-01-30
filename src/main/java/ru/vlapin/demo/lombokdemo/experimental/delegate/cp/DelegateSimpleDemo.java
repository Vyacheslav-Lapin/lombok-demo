package ru.vlapin.demo.lombokdemo.experimental.delegate.cp;

import lombok.Value;
import lombok.experimental.Delegate;

@Value
public class DelegateSimpleDemo {

  @Delegate
  A a;

  public DelegateSimpleDemo(int x) {
    this.a = new A(x);
  }
}

record A(int x) {
  public String method() {
    return "x = %d".formatted(x);
  }
}
