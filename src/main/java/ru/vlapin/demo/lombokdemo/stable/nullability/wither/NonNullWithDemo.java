package ru.vlapin.demo.lombokdemo.stable.nullability.wither;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.With;

@SuppressWarnings({"java:S4274", "java:S4973", "java:S125"})

@With
@AllArgsConstructor
public class NonNullWithDemo {
  @NonNull
  Integer x;

//public NonNullWithDemo withX(@NonNull Integer x) {
//  //noinspection ConstantValue
//  assert x != null : "x is marked non-null but is null";
//  //noinspection NumberEquality
//  return this.x == x ? this : new NonNullWithDemo(x);
//}

//public NonNullWithDemo(@NonNull Integer x) {
//  //noinspection ConstantValue
//  assert x != null : "x is marked non-null but is null";
//  this.x = x;
//}
}
