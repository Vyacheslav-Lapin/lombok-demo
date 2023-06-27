package ru.vlapin.demo.lombokdemo.stable.setter;

import lombok.Getter;
import lombok.Setter;

/**
 * SetterExample.
 */
public class SetterExample {
  @Setter
  @Getter
  private int x;

  // Зачем писать/генерить?
  // Итак же очевидно!
//public void setX(final int x) {
//  this.x = x;
//}
}
