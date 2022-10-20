package ru.vlapin.demo.lombokdemo.stable.constructors;

import java.beans.ConstructorProperties;

import lombok.AllArgsConstructor;

@AllArgsConstructor(
    // Как сгенерировать над конструктором:
    onConstructor_ = @ConstructorProperties(
        {"x", "s", "z"}))
@SuppressWarnings({"java:S125", "CommentedOutCode"})
public class ConstructorPropertiesDemo {
  private int x;
  private String s;
  private boolean z;

//  @ConstructorProperties({"x", "s", "z"})
//  public ConstructorPropertiesDemo(int x,
//                                   String s,
//                                   boolean z) {
//    this.x = x;
//    this.s = s;
//    this.z = z;
//  }
}
