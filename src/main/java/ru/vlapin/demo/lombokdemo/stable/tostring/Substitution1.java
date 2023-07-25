package ru.vlapin.demo.lombokdemo.stable.tostring;

import lombok.ToString;

/**
 * Substitution1.
 */
@SuppressWarnings({"unused", "java:S125", "FieldCanBeLocal"})
@ToString
public class Substitution1 {
  String s = """
      Lorem ipsum dolor sit amet, consectetur \
      adipiscing elit, sed do eiusmod tempor \
      incididunt ut labore et dolore magna \
      aliqua. Ut enim ad minim veniam, quis \
      nostrud exercitation ullamco laboris nisi \
      ut aliquip ex ea commodo consequat. Duis \
      aute irure dolor in reprehenderit in \
      voluptate velit esse cillum dolore eu \
      fugiat nulla pariatur. Excepteur sint \
      occaecat cupidatat non proident, sunt in \
      culpa qui officia deserunt mollit anim id \
      est laborum.""";

  @ToString.Include
  private String s() {
    return s.substring(0, 17) + "...";
  }

//@Override
//public String toString() {
//  return "Substitution1(s=" + s() + ")";
//}
}
