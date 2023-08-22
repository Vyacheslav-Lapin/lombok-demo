package ru.vlapin.demo.lombokdemo.experimental.getter.annotating;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.FirstAnnotation;
import ru.vlapin.demo.lombokdemo.SecondAnnotation;

import static org.assertj.core.api.Assertions.*;

/**
 * AnnotatingExample1Test.
 */
class AnnotatingOldStyleExampleTest {

  @Test
  @SneakyThrows
  @DisplayName("annotating via getter works correctly")
  void annotatingViaGetterWorksCorrectlyTest() {
    // when
    assertThat(AnnotatingOldStyleExample.class.getMethod("getX"))
        // then
        .isNotNull()
        .matches(getter -> getter.isAnnotationPresent(FirstAnnotation.class))
        .matches(getter -> getter.isAnnotationPresent(SecondAnnotation.class))
        .extracting(getter -> getter.getAnnotation(SecondAnnotation.class))
        .extracting(SecondAnnotation::value)
        .isEqualTo("str1");
  }
}
