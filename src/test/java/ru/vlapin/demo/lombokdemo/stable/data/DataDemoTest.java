package ru.vlapin.demo.lombokdemo.stable.data;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * DataDemoTest.
 */
class DataDemoTest {

  @Test
  @DisplayName("Data annotation works correctly")
  void dataAnnotationWorksCorrectlyTest() {
    // given
    val dataDemo = new DataDemo("lor")
        .setX(1).setB(true);

    val dataDemo1 = new DataDemo("lor")
        .setX(1).setB(true);

    val dataDemo2 = new DataDemo("lor")
        .setX(1).setB(false);

    // when
    assertThat(dataDemo)
        // then
        .isEqualTo(dataDemo1)
        .isNotEqualTo(dataDemo2)
        .hasToString("DataDemo(x=1, s=lor, b=true)")
        .extracting(DataDemo::getS)
        .isEqualTo("lor");
  }
}
