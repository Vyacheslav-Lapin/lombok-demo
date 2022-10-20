package ru.vlapin.demo.lombokdemo.stable.builder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

import static lombok.AccessLevel.*;

@Builder(
    access = AccessLevel.PROTECTED,
    builderClassName = "FooCreator",
    builderMethodName = "creator",
    buildMethodName = "create",
    setterPrefix = "with")
@AllArgsConstructor(access = PACKAGE)
@SuppressWarnings({"java:S125", "CommentedOutCode"})
public class BuilderBeforeClassCustomizationDemo {
  int x;
  String s;
  boolean b;

//  @ToString
//  @FieldDefaults(level = PRIVATE)
//  @NoArgsConstructor(access = PACKAGE)
//  protected static class FooCreator {
//    int x;
//    String s;
//    boolean b;
//
//    public FooCreator withX(final int x) {
//      this.x = x;
//      return this;
//    }
//
//    public FooCreator withS(final String s) {
//      this.s = s;
//      return this;
//    }
//
//    public FooCreator withB(final boolean b) {
//      this.b = b;
//      return this;
//    }
//
//    public BuilderBeforeClassCustomizationDemo create() {
//      return new BuilderBeforeClassCustomizationDemo(x, s, b);
//    }
//  }
//
//  protected static FooCreator creator() {
//    return new FooCreator();
//  }
}
