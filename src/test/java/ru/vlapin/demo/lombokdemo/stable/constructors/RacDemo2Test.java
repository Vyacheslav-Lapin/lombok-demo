package ru.vlapin.demo.lombokdemo.stable.constructors;

import static org.assertj.core.api.Assertions.*;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * RacDemo2Test.
 */
class RacDemo2Test {

  @Test
  @SneakyThrows
  @DisplayName("RequiredArgsConstructor with NonNull fields works correctly")
  void requiredArgsConstructorWithNonNullFieldsWorksCorrectlyTest() {
    // given
    val racDemo = new RacDemo2("lorem", true);

    // when
    assertThat(racDemo)
        // then
        .extracting("x", "s", "b")
        .contains(null, "lorem", true);
  }
}
