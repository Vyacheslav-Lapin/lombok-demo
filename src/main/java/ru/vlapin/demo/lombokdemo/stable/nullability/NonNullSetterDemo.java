package ru.vlapin.demo.lombokdemo.stable.nullability;

import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.NonFinal;
@SuppressWarnings("java:S125")

public class NonNullSetterDemo {
  @NonNull
  @Setter
  @NonFinal Integer x;

//public NonNullSetterDemo setX(@NonNull Integer x) {
//  if (x == null)
//    throw new NullPointerException("x is marked non-null but is null");
//  this.x = x;
//  return this;
//}
}
