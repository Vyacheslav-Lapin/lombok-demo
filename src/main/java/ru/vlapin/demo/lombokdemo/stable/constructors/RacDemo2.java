package ru.vlapin.demo.lombokdemo.stable.constructors;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;

/**
 * RacDemo1.
 */
@SuppressWarnings({"java:S125", "NullableProblems", "CommentedOutCode", "unused"})
@RequiredArgsConstructor
public class RacDemo2 {

  @NonFinal int x;

  String s;

  @NonNull @NonFinal boolean b;

//public RacDemo2(final String s,
//                @NonNull final boolean b) {
//  this.s = s;
//  this.b = b;
//}
}
