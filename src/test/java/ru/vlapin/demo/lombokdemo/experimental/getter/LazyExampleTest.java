package ru.vlapin.demo.lombokdemo.experimental.getter;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * LazyExampleTest.
 */
class LazyExampleTest {

  @Test
  @DisplayName("lazy getter works correctly")
  void lazyGetterWorksCorrectlyTest() {
    // given
    val lazy = new LazyExample();

    // when
    assertThat(lazy.getValue())
        // then
        .isNotNull()
        .hasSize(1_000_000);
  }
}
