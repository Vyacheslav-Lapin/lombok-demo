package ru.vlapin.demo.lombokdemo.stable.tostring;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * ToStringWithoutGetters1Test.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class ToStringWithoutGetters2Test {

  @Test
  @DisplayName("ToString annotation param doNotUseGetters works correctly")
  void toStringAnnotationParamDoNotUseGettersWorksCorrectlyTest() {
    // given
    val obj =
        new ToStringWithoutGetters2(
            5,
            "Lor");

    assertThat(ToStringWithoutGetters2.class)
        .hasPublicMethods("getX");

    // when
    assertThat(obj).isNotNull()
        // then
        .hasToString(
            "ToStringWithoutGetters2(" +
                "x=5, " +
                "s=Lor)");
  }
}
