package ru.vlapin.demo.lombokdemo.stable.getter;

import lombok.Getter;

public class GetterExample {
  @Getter
  private int x;

  // Зачем писать/генерить?
  // Итак же очевидно!
//@Generated
//@SuppressWarnings("all")
//@SuppressFBWarnings(justification = "generated code")
//public int getX() {
//  return this.x;
//}
}
