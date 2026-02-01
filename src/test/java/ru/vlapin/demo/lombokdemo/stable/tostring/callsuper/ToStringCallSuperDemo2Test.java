package ru.vlapin.demo.lombokdemo.stable.tostring.callsuper;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * ToStringCallSuperDemo2Test.
 */

@DisplayNameGeneration(ReplaceCamelCase.class)
class ToStringCallSuperDemo2Test {

  @Test
  @DisplayName("@ToString annotation with callSuper param works correctly")
  void toStringAnnotationWithCallSuperParamWorksCorrectlyTest() {
    // given
    val superDemo2 = new ToStringCallSuperDemo2(
        2, "Пупкин",
        1, "Вася");

    // when
    assertThat(superDemo2)
        // then
        .hasToString("ToStringCallSuperDemo2("
            + "super=ToStringDemo(x=2, s=Пупкин), "
            + "x1=1, s1=Вася)");
  }
}
