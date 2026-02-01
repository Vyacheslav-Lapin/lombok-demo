package ru.vlapin.demo.lombokdemo.stable.equalshashcode.canequals;

import static java.awt.Color.*;
import static org.assertj.core.api.Assertions.*;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
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
