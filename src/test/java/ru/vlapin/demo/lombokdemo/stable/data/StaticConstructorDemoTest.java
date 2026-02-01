package ru.vlapin.demo.lombokdemo.stable.data;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.stable.data.StaticConstructorDemo.*;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * StaticConstructorDemoTest.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class StaticConstructorDemoTest {

  @Test
  @SneakyThrows
  @DisplayName("Static constructor param of Data annotation works correctly")
  void staticConstructorParamOfDataAnnotationWorksCorrectlyTest() {
    // given
    val demo = StaticConstructorDemo("lor");

    // when
    assertThat(demo).isNotNull()
        // then
        .extracting(StaticConstructorDemo::getS)
        .isEqualTo("lor");
  }
}
