package ru.vlapin.demo.lombokdemo.stable.tostring;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * ToStringWithoutGetters1.
 */
@SuppressWarnings("java:S125")
@FieldDefaults(makeFinal = true)
@ToString
@Getter
@RequiredArgsConstructor
public class ToStringWithoutGetters2 {
  int x;
  String s;

  // А при наличии геттеров,
  // как указать обращаться к
  // полям напрямую?
  public String getS() {
    return s + " from getter";
  }

//@Override
//public String toString() {
//  return "ToStringWithoutGetters2(" +
//      "x=" + this.x + ", " +
//      "s=" + this.s + ")";
//}
}
