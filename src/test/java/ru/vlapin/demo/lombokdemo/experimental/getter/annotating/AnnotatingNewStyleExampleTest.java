package ru.vlapin.demo.lombokdemo.experimental.getter.annotating;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * AnnotatingExample1Test.
 */
class AnnotatingNewStyleExampleTest {

  @Test
  @SneakyThrows
  @DisplayName("getter annotating works correctly")
  void getterAnnotatingWorksCorrectlyTest() {

    // when
    assertThat(AnnotatingNewStyleExample.class.getMethod("getX"))
        // then
        .isNotNull()
        .matches(getter -> getter.isAnnotationPresent(FirstAnnotation.class))
        .matches(getter -> getter.isAnnotationPresent(SecondAnnotation.class))
        .extracting(getter -> getter.getAnnotation(SecondAnnotation.class))
        .extracting(SecondAnnotation::value)
        .isEqualTo("str2");
  }
}
