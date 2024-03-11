package ru.vlapin.demo.lombokdemo.stable.constructors;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        .extracting("x", "y", "z")
        .contains(1, "Lor", true);
  }
}
