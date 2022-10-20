package ru.vlapin.demo.lombokdemo.stable.builder;

import lombok.Builder;

//@AllArgsConstructor(access = PACKAGE)
//@AllArgsConstructor(access = PACKAGE,
//                    onConstructor_ = @Builder)
@Builder
@SuppressWarnings({"java:S125", "CommentedOutCode"})
public class BuilderBeforeClassDemo {
  int x;
  String s;
  boolean b;

//  @Setter
//  @ToString
//  @Accessors(fluent = true)
//  @FieldDefaults(level = PRIVATE)
//  @NoArgsConstructor(access = PACKAGE)
//  public static class BuilderBeforeClassDemoBuilder {
//    int x;
//    String s;
//    boolean b;

//    public BuilderBeforeClassDemo build() {
//      return new BuilderBeforeClassDemo(x, s, b);
//    }
//  }

//  public static BuilderBeforeClassDemo.BuilderBeforeClassDemoBuilder builder() {
//    return new BuilderBeforeClassDemo.BuilderBeforeClassDemoBuilder();
//  }
}
