package ru.vlapin.demo.lombokdemo.stable.equalshashcode.inheritance;

import lombok.EqualsAndHashCode;
import ru.vlapin.demo.lombokdemo.stable.equalshashcode.EahSimpleDemo;

@SuppressWarnings({"java:S125", "CommentedOutCode"})
@EqualsAndHashCode
public class EahInheritanceDemo2 extends EahSimpleDemo {

  boolean b;

  EahInheritanceDemo2(int x, String s, boolean b) {
    super(x, s);
    this.b = b; }

  // Как писать/генерировать методы equals
  // и hashCode при наследовании?
//public boolean equals(final Object o) {
//  return o == this
//      || o instanceof EahInheritanceDemo2 other
//      && other.canEqual(this)
//      && super.equals(o)
//      && b == other.b;
//}

//protected boolean canEqual(final Object other) {
//  return other instanceof EahInheritanceDemo2; }

//public int hashCode() {
//  return super.hashCode() * 59 + (b ? 79 : 97); }
}
