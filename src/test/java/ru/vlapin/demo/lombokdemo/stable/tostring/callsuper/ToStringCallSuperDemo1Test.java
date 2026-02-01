package ru.vlapin.demo.lombokdemo.stable.tostring.callsuper;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * ToStringCallSuperDemo1Test.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
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
