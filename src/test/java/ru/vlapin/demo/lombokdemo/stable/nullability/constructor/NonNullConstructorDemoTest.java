package ru.vlapin.demo.lombokdemo.stable.nullability.constructor;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
class NonNullConstructorDemoTest {

  @Test
  @DisplayName("NonNull before field on constructor works correctly")
  void nonNullBeforeFieldOnConstructorWorksCorrectlyTest() {
    //noinspection DataFlowIssue
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> new NonNullConstructorDemo(null))
        .withMessage("x is marked non-null but is null");
  }
}
