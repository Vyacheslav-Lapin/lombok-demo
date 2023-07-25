package ru.vlapin.demo.lombokdemo.stable.tostring;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * ToStringDemoTest.
 *
 * @author Vyacheslav Lapin
 */
class ToStringDemoTest {

  @Test
  @DisplayName("toString method works correctly")
  void toStringMethodWorksCorrectlyTest() {
    // given
    val demo = new ToStringDemo(
        123,
        "Lor");

    // when
    assertThat(demo)
        // then
        .hasToString("ToStringDemo(x=123, s=Lor)");
  }
}
