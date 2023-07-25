package ru.vlapin.demo.lombokdemo.stable.tostring.includefieldnames;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * ToStringIFNDemo2Test.
 */
class ToStringIFNDemo2Test {

  @Test
  @DisplayName("ToString param includeFieldNames works correctly")
  void toStringParamIncludeFieldNamesWorksCorrectlyTest() {
    // given
    val ifnDemo = new ToStringIFNDemo2(3, "Lorem");

    // when
    assertThat(ifnDemo).isNotNull()
        // then
        .hasToString(
            "ToStringIFNDemo2(3, Lorem)");
  }
}
