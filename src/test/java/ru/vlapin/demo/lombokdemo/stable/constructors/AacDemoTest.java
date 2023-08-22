package ru.vlapin.demo.lombokdemo.stable.constructors;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * AacDemoTest.
 */
class AacDemoTest {

  @Test
  @DisplayName("AllArgsConstructor annotation works correctly")
  void allArgsConstructorAnnotationWorksCorrectlyTest() {
    // given
    val obj = new AacDemo(1, "Lor", true);

    // when
    assertThat(obj)
        // then
        .extracting(AacDemo::getX,
            AacDemo::getY,
            AacDemo::isZ)
        .contains(1, "Lor", true);
  }
}
