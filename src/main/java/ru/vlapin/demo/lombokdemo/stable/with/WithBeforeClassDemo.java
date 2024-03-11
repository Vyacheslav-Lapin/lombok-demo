package ru.vlapin.demo.lombokdemo.stable.with;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.With;

@With
@RequiredArgsConstructor
public class WithBeforeClassDemo {
  @With(AccessLevel.NONE)
  int x;
//@With
  String s;
//@With
  boolean b;
}
