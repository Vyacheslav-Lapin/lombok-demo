package ru.vlapin.demo.lombokdemo.stable.equalshashcode.canequals;

import lombok.AllArgsConstructor;

//@EqualsAndHashCode
@AllArgsConstructor
public class Point2 {
  int x;
  int y;

  public boolean equals(final Object o) {
    return o == this
               || o instanceof Point2 point
                      && x == point.x
                      && y == point.y
                      && point.canEqual(this);
  }

  protected boolean canEqual(Object other) { return other instanceof Point2; }

  // @formatter:off
  public int hashCode() { return 31 * x + y; }
  // @formatter:on
}
