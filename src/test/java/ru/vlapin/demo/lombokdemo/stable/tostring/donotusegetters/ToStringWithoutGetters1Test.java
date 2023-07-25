package ru.vlapin.demo.lombokdemo.stable.tostring.donotusegetters;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * ToStringWithoutGetters1Test.
 */
class ToStringWithoutGetters1Test {

  @Test
  @DisplayName("ToString annotation param doNotUseGetters works correctly")
  void toStringAnnotationParamDoNotUseGettersWorksCorrectlyTest() {
    // given
    val obj =
        new ToStringWithoutGetters1(
            5,
            "Lor");

    assertThat(ToStringWithoutGetters1.class)
        .hasPublicMethods("getX");

    // when
    assertThat(obj).isNotNull()
        // then
        .hasToString(
            "ToStringWithoutGetters1(" +
                "x=5, " +
                "s=Lor)");
  }
}
