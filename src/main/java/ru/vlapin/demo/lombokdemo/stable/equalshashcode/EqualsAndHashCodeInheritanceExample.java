package ru.vlapin.demo.lombokdemo.stable.equalshashcode;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class EqualsAndHashCodeInheritanceExample extends EqualsAndHashCodeSimpleExample {

  private boolean b;

  EqualsAndHashCodeInheritanceExample(int x, String s, boolean b) {
    super(x, s);
    this.b = b;
  }

  // Как писать/генерировать методы equals и hashCode при наследовании?
//  @Override
//  public boolean equals(final Object o) {
//    return o == this
//               || o instanceof EqualsAndHashCodeInheritanceExample other
//                      && other.canEqual(this)
//                      && super.equals(o) && this.b == other.b;
//  }
//
//  @Override
//  protected boolean canEqual(final Object other) {
//    return other instanceof EqualsAndHashCodeInheritanceExample;
//  }
//
//  public int hashCode() {
//    return super.hashCode()
//               * 59 + (this.b ? 79 : 97);
//  }
}
