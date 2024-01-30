package ru.vlapin.demo.lombokdemo.stable.getter;

import static org.assertj.core.api.Assertions.*;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UnobtrusiveDemoTest {

  @Test
  @SneakyThrows
  @DisplayName("Custom setter works correctly")
  void customSetterWorksCorrectlyTest() {
    // given
    val unobtrusiveDemo = new UnobtrusiveDemo()
        .setX(55)
        .setB("true");

    // when
    assertThat(unobtrusiveDemo)
        // then
        .isNotNull()
        .extracting(
            UnobtrusiveDemo::isB,
            UnobtrusiveDemo::getX)
        .contains(true,"55");
  }
}
