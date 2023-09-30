package ru.vlapin.demo.lombokdemo.stable.value;

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
  @DisplayName("record works correctly")
  void recordWorksCorrectlyTest() {
    assertThat(new PointRecord(1, 2)).isNotNull()
        .extracting(PointRecord::x, PointRecord::y)
        .contains(1, 2);
  }

  @Test
  @DisplayName("value works correctly")
  void valueWorksCorrectlyTest() {
    assertThat(new PointValue(1, 2)).isNotNull()
        .extracting(PointValue::x, PointValue::y)
        .contains(1, 2);
  }
}
