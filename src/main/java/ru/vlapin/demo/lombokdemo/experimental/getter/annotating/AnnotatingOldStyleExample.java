package ru.vlapin.demo.lombokdemo.experimental.getter.annotating;

import lombok.Getter;

/**
 * AnnotatingExample1.
 */
@SuppressWarnings({"java:S125", "unused"})
public class AnnotatingOldStyleExample {

  @Getter(onMethod = @__({ @FirstAnnotation, @SecondAnnotation("str1") }))
  int x;

//  @FirstAnnotation
//  @SecondAnnotation("str1")
//  public int getX() {
//    return this.x;
//  }
}
