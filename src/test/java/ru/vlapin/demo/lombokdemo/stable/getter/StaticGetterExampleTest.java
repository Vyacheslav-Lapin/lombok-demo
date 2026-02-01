package ru.vlapin.demo.lombokdemo.stable.getter;

import static org.assertj.core.api.Assertions.*;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * StaticGetterExampleTest.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class StaticGetterExampleTest {

  @Test
  @SneakyThrows
  @DisplayName("Getter annotation works correctly on static field")
  void getterAnnotationWorksCorrectlyOnStaticFieldTest() {
    // when
    StaticGetterExample.setX(5);
    // then
    assertThat(StaticGetterExample.getX())
        .isEqualTo(5);
  }
}
