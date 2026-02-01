package ru.vlapin.demo.lombokdemo.stable.tostring;

import static org.assertj.core.api.Assertions.*;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * Substitution2Test.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class Substitution2Test {

  @Test
  @SneakyThrows
  @DisplayName("ToString.Include with name param works correctly")
  void toStringIncludeWithNameParamWorksCorrectlyTest() {
    // given
    val substitution = new Substitution2();

    // when
    assertThat(substitution).isNotNull()
        // then
        .hasToString("Substitution2(" +
            "longRead=Lorem ipsum dolor...)");
  }
}
