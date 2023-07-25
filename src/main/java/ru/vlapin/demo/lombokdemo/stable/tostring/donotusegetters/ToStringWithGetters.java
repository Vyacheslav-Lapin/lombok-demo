package ru.vlapin.demo.lombokdemo.stable.tostring.donotusegetters;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * ToStringGetters.
 */
@SuppressWarnings({"java:S125", "ClassCanBeRecord"})
@ToString
@Getter
@RequiredArgsConstructor
public class ToStringWithGetters {
  int x;
  String s;

  public String getS() {
    return s + " from getter";
  }

//@Override
//public String toString() {
//  return "ToStringWithGetters(" +
//      "x=" + this.getX() + ", " +
//      "s=" + this.getS() + ")";
//}
}
