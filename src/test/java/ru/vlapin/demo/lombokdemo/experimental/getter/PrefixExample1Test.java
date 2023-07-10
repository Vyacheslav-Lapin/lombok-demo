package ru.vlapin.demo.lombokdemo.experimental.getter;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * PrefixExample1Test.
 */
class PrefixExample1Test {

  @Test
  @SneakyThrows
  @DisplayName("Prefix param works correctly")
  void prefixParamWorksCorrectlyTest() {
    // given
    val lorem = new PrefixExample1()
        .setName("Lorem");

    // when
    assertThat(lorem).isNotNull()
        // then
        .extracting(PrefixExample1::getName)
        .isEqualTo("Lorem");
  }
}
