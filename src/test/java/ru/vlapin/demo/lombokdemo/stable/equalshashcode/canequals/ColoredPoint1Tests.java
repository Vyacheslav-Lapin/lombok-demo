package ru.vlapin.demo.lombokdemo.stable.equalshashcode.canequals;

import static java.awt.Color.*;
import static org.assertj.core.api.Assertions.*;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
class ColoredPoint1Tests {

  Point1 point = new Point1(1, 2);
  ColoredPoint1 coloredPoint1 = new ColoredPoint1(1, 2, BLUE);
  ColoredPoint1 coloredPoint2 = new ColoredPoint1(1, 2, RED);

  @Test
  @SneakyThrows
  @DisplayName("Equals works unsimmetry")
  void equalsWorksUnsymmetryTest() {
    assertThat(point).isEqualTo(coloredPoint1);
    assertThat(coloredPoint1).isNotEqualTo(point); // не симметрично
  }

  @Test
  @SneakyThrows
  @DisplayName("Equals works untransitively")
  void equalsWorksUntransitivelyTest() {
    assertThat(point)
        .isEqualTo(coloredPoint1)
        .isEqualTo(coloredPoint2);
    assertThat(coloredPoint1).isNotEqualTo(coloredPoint2);
  }
}
