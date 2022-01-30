package ru.vlapin.demo.lombokdemo.stable.equalshashcode.canequals;

import java.awt.Color;
import java.util.Objects;

import org.jetbrains.annotations.Contract;

public class ColoredPoint3 extends Point2 {

  Color color;

  ColoredPoint3(int x, int y, Color color) {
    super(x, y);
    this.color = color;
  }

  @Contract(value = "null -> false", pure = true)
  @Override public boolean equals(Object o) {
    return this == o
               || super.equals(o)
                      && o instanceof ColoredPoint3 coloredPoint
                      && Objects.equals(color, coloredPoint.color);
  }

  @Override
  protected boolean canEqual(Object other) {
    return other instanceof ColoredPoint3;
  }

  @Override public int hashCode() {
    return 31 * super.hashCode() + (
        color != null ? color.hashCode() : 0); }
}
