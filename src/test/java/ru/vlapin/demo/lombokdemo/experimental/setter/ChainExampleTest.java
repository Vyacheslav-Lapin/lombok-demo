package ru.vlapin.demo.lombokdemo.experimental.setter;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * ChainExampleTest.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
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
