package ru.vlapin.demo.lombokdemo.stable.tostring.includefieldnames;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * ToStringIFNDemo1.
 */
@SuppressWarnings("java:S125")
@ToString // lombok.tostring.includefieldnames = false
@RequiredArgsConstructor
public class ToStringIFNDemo2 {
  int x;
  String s;

  // А если мы не хотим выводить
  // имена полей?
//@Override
//public String toString() {
//  return "ToStringIFNDemo2("
//      + this.x + ", "
//      + this.s + ")";
//}
}
