package ru.vlapin.demo.lombokdemo.experimental.setter;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * MakeFinalExample.
 */
@Accessors(makeFinal = true)
@Getter
@Setter
@SuppressWarnings("java:S125")
public class MakeFinalExample {

  int x;

//public final int getX() {
//  return this.x;
//}

//public final void setX(final int x) {
//  this.x = x;
//}
}
