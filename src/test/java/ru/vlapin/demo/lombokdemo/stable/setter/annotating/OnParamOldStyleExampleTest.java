package ru.vlapin.demo.lombokdemo.stable.setter.annotating;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * OnParamOldStyleExampleTest.
 */
class OnParamOldStyleExampleTest {

  @Test
  @SneakyThrows
  @DisplayName("param annotated correctly - old style")
  void paramAnnotatedCorrectlyOldStyleTest() {
    // given
    val setX = OnParamOldStyleExample.class
        .getMethod("setX", int.class);

    // when
    assertThat(setX.getParameters()[0])
        // then
        .isNotNull()
        .extracting(param -> param.getAnnotation(MyAnno.class))
        .isInstanceOf(MyAnno.class);
  }
}
