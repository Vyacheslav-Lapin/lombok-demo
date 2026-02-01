package ru.vlapin.demo.lombokdemo.stable.constructors;

import static org.assertj.core.api.Assertions.*;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * RacDemo1Test.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
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
