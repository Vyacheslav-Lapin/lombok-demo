package ru.vlapin.demo.lombokdemo.stable.getter;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * AccessModifiersExample.
 */
public class AccessModifiersExample {
  @Getter(AccessLevel.PROTECTED)
  private int x;

  // А что, если нужен другой access
  // modifier? Скажем, protected?
//protected int getX() {
//  return this.x;
//}
}
