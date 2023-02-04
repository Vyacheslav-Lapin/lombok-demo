package ru.vlapin.demo.lombokdemo.experimental.utilityclass;

import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("java:S125")
public class UtilityClassExample {
  // public final class UtilityClassExample {

  private static final int CONSTANT = 5;
  // private final int CONSTANT = 5;

  // private UtilityClassExample() {
  //   throw new java.lang.UnsupportedOperationException(
  //       "This is a utility class and cannot be instantiated");
  // }

  public static int addSomething(int in) {
    // public /*static*/ int addSomething(int in) {
    return in + CONSTANT;
  }
}
