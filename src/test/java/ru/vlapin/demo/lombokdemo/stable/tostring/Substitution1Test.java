package ru.vlapin.demo.lombokdemo.stable.tostring;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Substitution1Test.
 */
class Substitution1Test {

  @Test
  @DisplayName("Include annotation under method works correctly")
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
