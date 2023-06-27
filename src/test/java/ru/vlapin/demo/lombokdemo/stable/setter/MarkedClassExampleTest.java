package ru.vlapin.demo.lombokdemo.stable.setter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Установленная над классом аннотация считается по умолчанию установленной
 * над всеми его полями.
 */
class MarkedClassExampleTest {

  @Test
  @DisplayName("marked class setter works correctly")
  void markedClassSetterWorksCorrectlyTest() {
    // given
    var mce = new MarkedClassExample();

    // when
    mce.setX(1);
    mce.setY(2);
    mce.setZ(3);

    // then
    assertThat(mce).isNotNull()
        .extracting(
            it -> it.x,
            it -> it.y,
            it -> it.z)
        .contains(1, 2, 3);
  }
}
