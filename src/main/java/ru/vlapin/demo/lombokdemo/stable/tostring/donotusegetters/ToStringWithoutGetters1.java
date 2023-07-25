package ru.vlapin.demo.lombokdemo.stable.tostring.donotusegetters;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * ToStringWithoutGetters1.
 */
@SuppressWarnings("java:S125")
@ToString(doNotUseGetters = true)
@Getter
@RequiredArgsConstructor
public class ToStringWithoutGetters1 {
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
//  return "ToStringWithoutGetters1(" +
//      "x=" + this.x + ", " +
//      "s=" + this.s + ")";
//}
}
