package ru.vlapin.demo.lombokdemo.experimental.unobtrusive;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UnobtrusiveDemo2Test {

  @Test
  @DisplayName("Custom setter works correctly near generated one")
  void customSetterWorksCorrectlyNearGeneratedOneTest() {
    // given
    val unobtrusiveDemo =
        new UnobtrusiveDemo2()
            .setB("true");

    // when
    assertThat(unobtrusiveDemo)
        // then
        .isNotNull()
        .extracting(UnobtrusiveDemo2::isB)
        .isEqualTo(true);

    // when
    assertThat(unobtrusiveDemo.setB(false))
        // then
        .isNotNull()
        .extracting(UnobtrusiveDemo2::isB)
        .isEqualTo(false);
  }
}
