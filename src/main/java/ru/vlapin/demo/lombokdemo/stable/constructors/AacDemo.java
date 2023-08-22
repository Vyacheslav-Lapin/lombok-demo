package ru.vlapin.demo.lombokdemo.stable.constructors;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * AacDemo.
 */
@SuppressWarnings({"java:S125", "CommentedOutCode", "ClassCanBeRecord"})
@AllArgsConstructor
@Getter
public class AacDemo {
  int x;
  String y;
  boolean z;

//public AacDemo(final int x,
//               final String y,
//               final boolean z) {
//  this.x = x;
//  this.y = y;
//  this.z = z;
//}
}
