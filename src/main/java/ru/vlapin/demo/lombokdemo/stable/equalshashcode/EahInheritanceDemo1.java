package ru.vlapin.demo.lombokdemo.stable.equalshashcode;

import lombok.EqualsAndHashCode;

@SuppressWarnings({"java:S125", "CommentedOutCode"})
@EqualsAndHashCode(callSuper = true)
public class EahInheritanceDemo1 extends EahSimpleDemo {

  boolean b;

  EahInheritanceDemo1(int x, String s, boolean b) {
    super(x, s);
    this.b = b; }

  // Как писать/генерировать методы equals
  // и hashCode при наследовании?
//public boolean equals(final Object o) {
//  return o == this
//      || o instanceof EahInheritanceDemo1 other
//      && other.canEqual(this)
//      && super.equals(o)
//      && b == other.b;
//}

//protected boolean canEqual(final Object other) {
//  return other instanceof EahInheritanceDemo1; }

//public int hashCode() {
//  return super.hashCode() * 59 + (b ? 79 : 97); }
}
