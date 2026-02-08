package ru.vlapin.demo.lombokdemo.common;

import static org.assertj.core.api.Assertions.*;

import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * TestUtilsTest.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
@ExtensionMethod(value = CharSequenceUtil.class, suppressBaseMethods = false)
class TestUtilsTest {

  @Test
  @SneakyThrows
//  @DisplayName("camel case transformer works correctly")
  void camelCaseTransformerWorksCorrectlyTest() {
    // when
    assertThat("fghGhRffY".camelCaseToSpacedString()).isNotNull()
        // then
        .hasToString("fgh gh rff y");
  }
}
