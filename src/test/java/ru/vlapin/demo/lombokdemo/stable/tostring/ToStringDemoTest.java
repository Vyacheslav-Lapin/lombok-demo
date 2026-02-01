package ru.vlapin.demo.lombokdemo.stable.tostring;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * ToStringDemoTest.
 *
 * @author Vyacheslav Lapin
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
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
