package ru.vlapin.demo.lombokdemo.stable.equalshashcode.canequals;

import static java.awt.Color.*;
import static org.assertj.core.api.Assertions.*;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
class SmellPointTests {

  Point1 point = new Point1(1, 2);
  ColoredPoint2 coloredPoint = new ColoredPoint2(1, 2, BLUE);
  SmellPoint smellPoint = new SmellPoint(1, 2, Smell.BAD);

  @Test
  @SneakyThrows
  @DisplayName("Equals works unsymmetrical")
  @SuppressWarnings("AssertBetweenInconvertibleTypes")
  void equalsWorksUnsymmetricalTest() {
    assertThat(smellPoint).isEqualTo(coloredPoint);
    assertThat(coloredPoint).isEqualTo(smellPoint); // симметрично
  }

  @Test
  @SneakyThrows
  @DisplayName("Equals works intransitively")
  @SuppressWarnings("AssertBetweenInconvertibleTypes")
  void equalsWorksIntransitivelyTest() {
    assertThat(point)
        .isEqualTo(coloredPoint)
        .isEqualTo(smellPoint);
    assertThat(coloredPoint).isEqualTo(smellPoint); // транзитивно
  }
}
