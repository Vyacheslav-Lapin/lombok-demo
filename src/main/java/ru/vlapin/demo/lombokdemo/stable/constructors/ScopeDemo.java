package ru.vlapin.demo.lombokdemo.stable.constructors;

import lombok.AllArgsConstructor;

import static lombok.AccessLevel.*;

/**
 * ScopeDemo.
 */
@SuppressWarnings({"java:S125", "CommentedOutCode", "unused"})
@AllArgsConstructor(access = PRIVATE)
public class ScopeDemo {
  int x;
  String y;
  boolean z;

  // Как указать область видимости
  // конструктору?
//private ScopeDemo(final int x,
//                  final String y,
//                  final boolean z) {
//  this.x = x;
//  this.y = y;
//  this.z = z;
//}
}
