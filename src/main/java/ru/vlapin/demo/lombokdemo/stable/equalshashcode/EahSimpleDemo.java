package ru.vlapin.demo.lombokdemo.stable.equalshashcode;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@SuppressWarnings({"java:S125", "CommentedOutCode", /*"ConstantValue"*/})
@EqualsAndHashCode
@RequiredArgsConstructor
public class EahSimpleDemo {
  int x;
  String s;

  // Как писать/генерировать equals и hashCode?
//public boolean equals(final Object o) {
//  return o == this
//      || o instanceof EahSimpleDemo other
//      && other.canEqual(this)
//      && x == other.x
//      && Objects.equals(s, other.s);
//}

//protected boolean canEqual(final Object other) {
//  return other instanceof EahSimpleDemo;
//}

//public int hashCode() {
//  return (x + 59) * 59
//      + (s == null ? 43 : s.hashCode()); }
}
