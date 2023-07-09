package ru.vlapin.demo.lombokdemo.experimental.setter.annotating;

import lombok.Setter;

/**
 * OnParamOldStyleExample.
 */
@SuppressWarnings({"unused", "java:S125"})
public class OnParamOldStyleExample {

  @Setter(onParam = @__(@MyAnno))
  int x;

//public void setX(@MyAnno final int x) {
//  this.x = x;
//}
}
