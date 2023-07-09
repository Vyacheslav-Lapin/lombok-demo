package ru.vlapin.demo.lombokdemo.experimental.getter.annotating;

import lombok.Getter;

/**
 * AnnotatingExample2.
 */
@SuppressWarnings({"java:S125", "unused"})
public class AnnotatingNewStyleExample {

  @Getter(onMethod_ = {@FirstAnnotation, @SecondAnnotation("str2")})
  int x;

//@FirstAnnotation
//@SecondAnnotation("str1")
//public int getX() {
//  return this.x;
//}
}
