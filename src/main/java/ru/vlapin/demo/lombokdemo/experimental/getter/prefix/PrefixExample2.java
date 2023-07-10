package ru.vlapin.demo.lombokdemo.experimental.getter.prefix;

import lombok.Getter;
import lombok.Setter;

/**
 * PrefixExample.
 */
@SuppressWarnings("java:S125")
public class PrefixExample2 {

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
