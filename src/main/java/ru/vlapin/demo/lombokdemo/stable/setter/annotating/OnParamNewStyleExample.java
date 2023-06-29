package ru.vlapin.demo.lombokdemo.stable.setter.annotating;

import lombok.Setter;

/**
 * OnParamNewStyleExample.
 */
@SuppressWarnings({"unused", "java:S125"})
public class OnParamNewStyleExample {

  @Setter(onParam_ = @MyAnno)
  String s;

//public void setS(@MyAnno final String s) {
//  this.s = s;
//}
}
