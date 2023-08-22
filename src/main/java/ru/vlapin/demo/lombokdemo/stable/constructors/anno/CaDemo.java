package ru.vlapin.demo.lombokdemo.stable.constructors.anno;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.With;
import lombok.experimental.NonFinal;

@SuppressWarnings({"java:S125", "CommentedOutCode"})
@Builder
@AllArgsConstructor
public class CaDemo {
  @With @Setter @NonFinal
  @MyAnno
  int x;
  String s;
  boolean b;

//public CaDemo(@MyAnno final int x,
//              final String s,
//              final boolean b) {
//  this.x = x;
//  this.s = s;
//  this.b = b;
//}

//@Setter
//@ToString
//@NoArgsConstructor
//@Accessors(fluent = true)
//public static class CaDemoBuilder {
//  @NonFinal int x;
//  @NonFinal String s;
//  @NonFinal boolean b;
//
//  public CaDemo.CaDemoBuilder x(@MyAnno final int x) {
//    this.x = x;
//    return this;
//  }
//
//  public CaDemo build() {
//    return new CaDemo(this.x, this.s, this.b);
//  }
//}
//
//public static CaDemo.CaDemoBuilder builder() {
//  return new CaDemo.CaDemoBuilder();
//}
//
//public CaDemo withX(@MyAnno final int x) {
//  return this.x == x ? this : new CaDemo(x, this.s, this.b);
//}
//
//public CaDemo setX(@MyAnno final int x) {
//  this.x = x;
//  return this;
//}
}
