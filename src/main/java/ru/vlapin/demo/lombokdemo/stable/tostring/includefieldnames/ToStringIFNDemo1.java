package ru.vlapin.demo.lombokdemo.stable.tostring.includefieldnames;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * ToStringIFNDemo1.
 */
@SuppressWarnings("java:S125")
@ToString(includeFieldNames = false)
@RequiredArgsConstructor
public class ToStringIFNDemo1 {
  int x;
  String s;

  // А если мы не хотим выводить
  // имена полей?
//@Override
//public String toString() {
//  return "ToStringIFNDemo1("
//      + this.x + ", "
//      + this.s + ")";
//}
}
