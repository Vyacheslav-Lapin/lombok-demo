package ru.vlapin.demo.lombokdemo.experimental.utilityclass;

import lombok.experimental.UtilityClass;

@SuppressWarnings({"java:S125", "CommentedOutCode"})
@UtilityClass
public /*final*/ class UtilityClassExample {

  private /*static*/ final int CONSTANT = 5;

//private UtilityClassExample() {
// throw new java.lang.UnsupportedOperationException(
//     "This is a utility class and cannot be instantiated");
//}

  public /*static*/ int addSomething(int in) {
    return in + CONSTANT;
  }
}
