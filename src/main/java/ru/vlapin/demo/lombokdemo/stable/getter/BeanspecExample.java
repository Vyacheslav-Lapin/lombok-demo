package ru.vlapin.demo.lombokdemo.stable.getter;

import lombok.Getter;
import lombok.Setter;

public class BeanspecExample {

  @Getter
  @Setter
  private int uShape;

//  public int getuShape() {
//    return this.uShape;
//  }
//
//  public BeanspecExample setuShape(int uShape) {
//    this.uShape = uShape;
//    return this;
//  }
}
