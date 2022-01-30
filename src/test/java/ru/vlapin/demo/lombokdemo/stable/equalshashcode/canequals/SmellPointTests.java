package ru.vlapin.demo.lombokdemo.stable.equalshashcode.canequals;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.awt.Color.*;
import static org.assertj.core.api.Assertions.*;

class SmellPointTests {

  Point1 point = new Point1(1, 2);
  ColoredPoint2 coloredPoint = new ColoredPoint2(1, 2, BLUE);
  SmellPoint smellPoint = new SmellPoint(1, 2, Smell.BAD);

  @Test
  @SneakyThrows
  @DisplayName("Equals works unsimmetry")
  void equalsWorksUnsimmetryTest() {
    assertThat(smellPoint).isEqualTo(coloredPoint);
    assertThat(coloredPoint).isEqualTo(smellPoint); // симметрично
  }

  @Test
  @SneakyThrows
  @DisplayName("Equals works untransitively")
  void equalsWorksUntransitivelyTest() {
    assertThat(point)
        .isEqualTo(coloredPoint)
        .isEqualTo(smellPoint);
    assertThat(coloredPoint).isEqualTo(smellPoint); // транзитивно
  }
}
