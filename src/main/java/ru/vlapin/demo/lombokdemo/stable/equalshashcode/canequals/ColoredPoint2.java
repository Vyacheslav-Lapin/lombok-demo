package ru.vlapin.demo.lombokdemo.stable.equalshashcode.canequals;

import java.awt.Color;
import java.util.Objects;

public class ColoredPoint2 extends Point1 {

  Color color;

  ColoredPoint2(int x, int y, Color color) {
    super(x, y);
    this.color = color;
  }

  @Override public boolean equals(Object o) {
    // Если о — обычная точка, сравнение не учитывает цвет
    //о — объект ColorPoint; выполняется полное сравнение
    return o instanceof Point1 point
               && super.equals(point)
               && (!(point instanceof ColoredPoint2 coloredPoint)
                       || Objects.equals(color, coloredPoint.color));
  }

  @Override public int hashCode() {
    return 31 * super.hashCode() + (color != null ? color.hashCode() : 0);
  }
}
