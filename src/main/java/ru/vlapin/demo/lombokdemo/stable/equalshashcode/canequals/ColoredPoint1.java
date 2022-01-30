package ru.vlapin.demo.lombokdemo.stable.equalshashcode.canequals;

import java.awt.Color;
import java.util.Objects;

public class ColoredPoint1 extends Point1 {

  Color color;

  ColoredPoint1(int x, int y, Color color) {
    super(x, y);
    this.color = color;
  }

  @Override public boolean equals(Object o) {
    return this == o
               || super.equals(o)
                      && o instanceof ColoredPoint1 coloredPoint
                      && Objects.equals(color, coloredPoint.color);

  }

  @Override public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (color != null ? color.hashCode() : 0);
    return result;
  }
}
