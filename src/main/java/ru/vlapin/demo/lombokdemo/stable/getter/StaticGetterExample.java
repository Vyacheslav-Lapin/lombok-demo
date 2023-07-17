package ru.vlapin.demo.lombokdemo.stable.getter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.*;

/**
 * StaticGetterExample.
 */
@SuppressWarnings({"CommentedOutCode", "java:S125"})
@NoArgsConstructor(access = PRIVATE)
public class StaticGetterExample {
  @Getter
  @Setter
  private static int x;

  //public static int getX() {
  //  return StaticGetterExample.x;
  //}

  //public static void setX(final int x) {
  //  StaticGetterExample.x = x;
  //}
}
