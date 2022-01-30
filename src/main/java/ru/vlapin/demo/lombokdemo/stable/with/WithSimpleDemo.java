package ru.vlapin.demo.lombokdemo.stable.with;

import lombok.AllArgsConstructor;
import lombok.With;

//Решение:
@With
@AllArgsConstructor
public class WithSimpleDemo {
  int x;
  String s;
  boolean b;

//  public WithSimpleDemo withX(final int x) {
//    return this.x == x ? this : new WithSimpleDemo(x, this.s, this.b);
//  }

//  public WithSimpleDemo withS(final String s) {
//    return this.s == s ? this : new WithSimpleDemo(this.x, s, this.b);
//  }

//  public WithSimpleDemo withB(final boolean b) {
//    return this.b == b ? this : new WithSimpleDemo(this.x, this.s, b);
//  }
}
