package ru.vlapin.demo.lombokdemo.math;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RationalTest {

  @Test
  @DisplayName("Rational works correctly")
  void rationalWorksCorrectlyTest() {
    // given
    val rational = new Rational(5, 4);

    // when
    assertThat(rational.lessThen(1L)).isNotNull()
                                     // then
                                     .isFalse();
  }
}
