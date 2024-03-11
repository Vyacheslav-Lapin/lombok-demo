package ru.vlapin.demo.lombokdemo.stable.nullability;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NonNullSetterDemoTest {

  @Test
  @DisplayName("NonNull before field on Setter works correctly")
  void nonNullBeforeFieldOnSetterWorksCorrectlyTest() {
    // given
    val demo = new NonNullSetterDemo();

    //noinspection DataFlowIssue
    assertThatExceptionOfType(NullPointerException.class)
        .isThrownBy(() -> demo.setX(null))
        .withMessage("x is marked non-null but is null");
  }
}
