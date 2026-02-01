package ru.vlapin.demo.lombokdemo.stable.constructors;

import static org.assertj.core.api.Assertions.*;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * RacDemo2Test.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
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
