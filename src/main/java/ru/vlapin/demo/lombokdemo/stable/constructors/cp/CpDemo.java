package ru.vlapin.demo.lombokdemo.stable.constructors.cp;

import lombok.RequiredArgsConstructor;

@SuppressWarnings("java:S125")
@RequiredArgsConstructor//(
//  onConstructor_ =
//    @ConstructorProperties({
//        "x", "s", "z"}))
public class CpDemo {
  int x;
  String s;
  boolean b;
}
