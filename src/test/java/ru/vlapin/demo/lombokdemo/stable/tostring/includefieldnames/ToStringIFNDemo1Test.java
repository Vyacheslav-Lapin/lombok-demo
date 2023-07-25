package ru.vlapin.demo.lombokdemo.stable.tostring.includefieldnames;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * ToStringIFNDemo1Test.
 */
class ToStringIFNDemo1Test {

  @Test
  @DisplayName("ToString param includeFieldNames works correctly")
  void toStringParamIncludeFieldNamesWorksCorrectlyTest() {
    // given
    val ifnDemo = new ToStringIFNDemo1(
        3, "Lorem");

    // when
    assertThat(ifnDemo).isNotNull()
        // then
        .hasToString(
            "ToStringIFNDemo1(3, Lorem)");
  }
}
