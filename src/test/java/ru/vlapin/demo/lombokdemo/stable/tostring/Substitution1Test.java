package ru.vlapin.demo.lombokdemo.stable.tostring;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * Substitution1Test.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class Substitution1Test {

  @Test
  @DisplayName("@ToString.Include annotation under method works correctly")
  void includeAnnotationUnderMethodWorksCorrectlyTest() {
    // given
    val substitution = new Substitution1();

    // when
    assertThat(substitution).isNotNull()
        // then
        .hasToString("Substitution1(" +
            "s=Lorem ipsum dolor...)");
  }
}
