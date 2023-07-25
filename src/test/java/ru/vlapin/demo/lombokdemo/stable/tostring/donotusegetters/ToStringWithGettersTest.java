package ru.vlapin.demo.lombokdemo.stable.tostring.donotusegetters;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * ToStringGettersTest.
 */
class ToStringWithGettersTest {

  @Test
  @DisplayName("ToString delegates to getters correctly")
  void toStringDelegatesToGettersCorrectlyTest() {
    // given
    val obj = new ToStringWithGetters(5, "lor");

    // when
    assertThat(obj).isNotNull()
        // then
        .hasToString(
            "ToStringWithGetters(" +
                "x=5, " +
                "s=lor from getter)");
  }
}
