package ru.vlapin.demo.lombokdemo.stable.with;

import lombok.RequiredArgsConstructor;
import lombok.With;

@SuppressWarnings({
    "java:S125",
    "CommentedOutCode",
    "FieldCanBeLocal",
    "unused"})

@RequiredArgsConstructor
public class WithDemo {
  @With
  int x;
  String s;
  boolean b;

//public WithDemo withX(int x) {
//  return this.x == x ? this
//      : new WithDemo(x, this.s, this.b);
//}
}
