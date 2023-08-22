package ru.vlapin.demo.lombokdemo.stable.constructors;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.stable.constructors.WithoutNewDemo.WithoutNewDemo;

/**
 * WithoutNewDemoTest.
 */
class WithoutNewDemoTest {

  @Test
  @DisplayName("Object creates correctly without new keyword")
  void objectCreatesCorrectlyWithoutNewKeywordTest() {
    // given
    val obj = WithoutNewDemo(1, "lor", true);

    // when
    assertThat(obj).isNotNull()
        // then
        .extracting(
            WithoutNewDemo::getX,
            WithoutNewDemo::getS,
            WithoutNewDemo::isB)
        .contains(1, "lor", true);
  }
}
