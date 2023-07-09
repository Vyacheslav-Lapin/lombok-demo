package ru.vlapin.demo.lombokdemo.experimental.setter;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * ChainExampleTest.
 */
class ChainExampleTest {

  @Test
  @DisplayName("chain accessor works correctly")
  void chainAccessorWorksCorrectlyTest() {
    // given
    val ace = new ChainExample()
        .setX(1)
        .setS("Lorem ipsum dolor sit amet");

    // when
    assertThat(ace).isNotNull()
        // then
        .extracting(
            ChainExample::getX,
            ChainExample::getS)
        .contains(1, "Lorem ipsum dolor sit amet");
  }
}
