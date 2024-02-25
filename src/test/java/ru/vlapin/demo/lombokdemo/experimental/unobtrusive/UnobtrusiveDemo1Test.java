package ru.vlapin.demo.lombokdemo.experimental.unobtrusive;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UnobtrusiveDemo1Test {

  @Test
  @DisplayName("Custom setter works correctly")
  void customSetterWorksCorrectlyTest() {
    // given
    val unobtrusiveDemo =
        new UnobtrusiveDemo1()
            .setX(55)
            .setB("true");

    // when
    assertThat(unobtrusiveDemo)
        // then
        .isNotNull()
        .extracting(
            UnobtrusiveDemo1::isB,
            UnobtrusiveDemo1::getX)
        .contains(true,"55");
  }
}
