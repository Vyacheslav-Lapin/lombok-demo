package ru.vlapin.demo.lombokdemo.experimental.getter;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * PrefixExample.
 */
@SuppressWarnings("java:S125")
public class PrefixExample1 {

  @Accessors(prefix = "f")
  @Getter
  @Setter
  String fName = "Hello, World!";

//public String getName() {
//  return this.fName;
//}

//public PrefixExample setName(final String fName) {
//  this.fName = fName;
//  return this;
//}
}
