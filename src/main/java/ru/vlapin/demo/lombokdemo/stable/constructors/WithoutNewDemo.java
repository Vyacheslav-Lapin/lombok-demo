package ru.vlapin.demo.lombokdemo.stable.constructors;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * WithoutNewDemo.
 */
@SuppressWarnings({"java:S125", "unused"})
@AllArgsConstructor(staticName = "WithoutNewDemo")
//@AllArgsConstructor(access = PRIVATE)
@Getter
public class WithoutNewDemo {
  int x;
  String s;
  boolean b;

//public static WithoutNewDemo WithoutNewDemo(final int x,
//                                            final String s,
//                                            final boolean b) {
//  return new WithoutNewDemo(x, s, b);
//}
}
