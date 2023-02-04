package ru.vlapin.demo.lombokdemo.stable.equalshashcode.canequals;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.awt.Color.*;
import static org.assertj.core.api.Assertions.*;

class ColoredPoint3Tests {

  Point2 point = new Point2(1, 2);
  ColoredPoint3 coloredPoint1 = new ColoredPoint3(1, 2, BLUE);
  ColoredPoint3 coloredPoint2 = new ColoredPoint3(1, 2, RED);

  @Test
  @SneakyThrows
  @DisplayName("Equals works symmetry")
  void equalsWorksSymmetryTest() {
    assertThat(point).isNotEqualTo(coloredPoint1);
    assertThat(coloredPoint1).isNotEqualTo(point); // симметрично
  }

  @Test
  @SneakyThrows
  @DisplayName("Equals works transitively")
  void equalsWorksTransitivelyTest() {
    assertThat(point)
        .isNotEqualTo(coloredPoint1)
        .isNotEqualTo(coloredPoint2);
    assertThat(coloredPoint1).isNotEqualTo(coloredPoint2); // транзитивно
  }
}
