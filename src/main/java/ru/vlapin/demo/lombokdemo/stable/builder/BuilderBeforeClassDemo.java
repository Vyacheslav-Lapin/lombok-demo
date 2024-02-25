package ru.vlapin.demo.lombokdemo.stable.builder;

import lombok.Builder;

//@AllArgsConstructor(access = PACKAGE,
//                    onConstructor_ = @Builder)
@SuppressWarnings({"java:S125", "CommentedOutCode"})
@Builder
//@AllArgsConstructor(access = PACKAGE)
public class BuilderBeforeClassDemo {
  int x;
  String s;
  boolean b;

//public static BuilderBeforeClassDemoBuilder builder() {
//  return new BuilderBeforeClassDemoBuilder();
//}

//@Setter
//@ToString
//@Accessors(fluent = true)
//@FieldDefaults(level = PRIVATE)
//@NoArgsConstructor(access = PACKAGE)
//public static class BuilderBeforeClassDemoBuilder {
//  int x;
//  String s;
//  boolean b;

//  public BuilderBeforeClassDemo build() {
//    return new BuilderBeforeClassDemo(x, s, b);
//  }
//}
}
