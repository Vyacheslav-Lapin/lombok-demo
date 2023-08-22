package ru.vlapin.demo.lombokdemo.stable.constructors;

import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;

/**
 * RacDemo1.
 */
@SuppressWarnings({"java:S125", "CommentedOutCode", "unused"})
@RequiredArgsConstructor
public class RacDemo1 {

  @NonFinal int x;
  String s;
  @NonFinal boolean b;

//public RacDemo1(final String s) {
//  this.s = s;
//}
}
