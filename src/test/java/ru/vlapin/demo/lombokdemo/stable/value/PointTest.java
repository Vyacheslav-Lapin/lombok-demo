package ru.vlapin.demo.lombokdemo.stable.value;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * PointTest.
 *
 * @author Vyacheslav Lapin
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class PointTest {

  @Test
  //@DisplayName("record works correctly")
  void recordWorksCorrectlyTest() {
    assertThat(new PointRecord(1, 2)).isNotNull()
        .extracting(PointRecord::x, PointRecord::y)
        .contains(1, 2);
  }

  @Test
  //@DisplayName("value works correctly")
  void valueWorksCorrectlyTest() {
    assertThat(new PointValue(1, 2)).isNotNull()
        .extracting(PointValue::x, PointValue::y)
        .contains(1, 2);
  }
}
