package ru.vlapin.demo.lombokdemo.stable.constructors;

import lombok.AllArgsConstructor;
import ru.vlapin.demo.lombokdemo.FirstAnnotation;
import ru.vlapin.demo.lombokdemo.SecondAnnotation;

/**
 * Annotation set before constructor old style demo.
 */
@SuppressWarnings({"java:S125", "CommentedOutCode", "unused"})
@AllArgsConstructor(
    onConstructor_ = {
        @FirstAnnotation,
        @SecondAnnotation("value"),
    })
public class A8nNewStyleDemo {
  int x;
  String s;
  boolean b;

//@FirstAnnotation
//@SecondAnnotation("value")
//public A8nNewStyleDemo(final int x,
//                       final String s,
//                       final boolean b) {
//  this.x = x;
//  this.s = s;
//  this.b = b;
//}
}
