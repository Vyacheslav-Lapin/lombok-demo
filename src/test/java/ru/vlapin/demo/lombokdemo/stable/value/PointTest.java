package ru.vlapin.demo.lombokdemo.stable.value;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * PointTest.
 *
 * @author Vyacheslav Lapin
 */
class PointTest {

  @Test
  @SneakyThrows
  @DisplayName("record works correctly")
  void recordWorksCorrectlyTest() {
    assertThat(new Point(1, 2)).isNotNull()
        .extracting(Point::x, Point::y)
        .contains(1, 2);
  }
}
