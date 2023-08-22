package ru.vlapin.demo.lombokdemo.stable.constructors;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * RacDemo1Test.
 */
class RacDemo1Test {

  @Test
  @SneakyThrows
  @DisplayName("RequiredArgsConstructor annotation works correctly")
  void requiredArgsConstructorAnnotationWorksCorrectlyTest() {
    // given
    val racDemo = new RacDemo1("lorem");

    // when
    assertThat(racDemo)
        // then
        .extracting("x", "s", "b")
        .contains(0, "lorem", false);
  }
}
