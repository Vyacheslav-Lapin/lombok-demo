package ru.vlapin.demo.lombokdemo.stable.tostring;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Order.
 */
@SuppressWarnings("java:S125")
@ToString
@AllArgsConstructor
public class Order {

  int x;

  @ToString.Include(rank = 1)
  String s;

//@Override
//public String toString() {
//  return "Order(" +
//      "s=" + s +
//      ", x=" + x + ")";
//}
}
