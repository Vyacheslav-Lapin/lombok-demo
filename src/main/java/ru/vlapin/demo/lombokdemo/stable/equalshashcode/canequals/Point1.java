package ru.vlapin.demo.lombokdemo.stable.equalshashcode.canequals;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Point1 {
  int x;
  int y;

  @Override public boolean equals(Object o) {
    return this == o
               || o instanceof Point1 point
                      && x == point.x
                      && y == point.y;
  }

  // @formatter:off
  @Override public int hashCode() { return 31 * x + y; }
  // @formatter:on
}
