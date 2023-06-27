package ru.vlapin.demo.lombokdemo.stable.getter.beanspec;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BeanspecExample {

  // Как уменьшить регистр первой
  // буквы в getter'е/setter'е, если
  // вторая – заглавная?
  private int uShape;

//public int getuShape() {
//  return this.uShape;
//}

//public void setuShape(int uShape) {
//  this.uShape = uShape;
//}
}
