package ru.vlapin.demo.lombokdemo.stable.getter;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * MarkedClassExampleTest.
 */
class MarkedClassExampleTest {

  @Test
  @DisplayName("getter annotation for class works correctly")
  void getterAnnotationForClassWorksCorrectlyTest() {
    // given
    val mce = new MarkedClassExample();

    // when
    mce.setX(1);
    mce.setY(2);
    mce.setZ(3);

    // then
    assertThat(mce).isNotNull()
        .extracting(
            MarkedClassExample::getX,
            MarkedClassExample::getY,
            MarkedClassExample::getZ)
        .contains(1, 2, 3);
  }
}
