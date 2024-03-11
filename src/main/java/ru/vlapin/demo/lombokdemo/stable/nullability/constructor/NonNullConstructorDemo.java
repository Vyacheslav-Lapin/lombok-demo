package ru.vlapin.demo.lombokdemo.stable.nullability.constructor;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@SuppressWarnings({"unused", "java:S125", "CommentedOutCode"})

@RequiredArgsConstructor
public class NonNullConstructorDemo {
  @NonNull
  Integer x;

//public NonNullConstructorDemo(@NonNull Integer x) {
//  if (x == null)
//    throw new IllegalArgumentException("x is marked non-null but is null");
//  this.x = x;
//}
}
