package ru.vlapin.demo.lombokdemo.stable.nullability.wither;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
class NonNullWithDemoTest {

  @Test
  //@DisplayName("NonNull before field on wither works correctly")
  void nonNullBeforeFieldOnWitherWorksCorrectlyTest() {
    // given
    val demo = new NonNullWithDemo(1);

    //noinspection DataFlowIssue,ResultOfMethodCallIgnored
    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> demo.withX(null))
        .withMessage("x is marked non-null but is null");
  }
}
