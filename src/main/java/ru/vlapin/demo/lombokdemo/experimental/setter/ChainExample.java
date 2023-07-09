package ru.vlapin.demo.lombokdemo.experimental.setter;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * ChainExample.
 */
@Accessors(chain = true)
@Setter
@Getter
@SuppressWarnings("java:S125")
public class ChainExample {
  int x;
  String s;

//public ChainExample setX(final int x) {
//  this.x = x;
//  return this;
//}

//public ChainExample setS(final String s) {
//  this.s = s;
//  return this;
//}
}
