package ru.vlapin.demo.lombokdemo.stable.tostring.callsuper;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * ToStringCallSuperDemo1Test.
 */
class ToStringCallSuperDemo1Test {

  @Test
  @DisplayName("ToString annotation with callsuper param works correctly")
  void toStringAnnotationWithCallsuperParamWorksCorrectlyTest() {
    // given
    val superDemo1 =
        new ToStringCallSuperDemo1(
            2, "Пупкин",
            1, "Вася");

    // when
    assertThat(superDemo1)
        // then
        .hasToString("ToStringCallSuperDemo1("
            + "super=ToStringDemo(x=2, s=Пупкин), "
            + "x1=1, s1=Вася)");
  }
}
