package ru.vlapin.demo.lombokdemo.stable.setter.annotating;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * OnParamNewStyleExampleTest.
 */
class OnParamNewStyleExampleTest {

  @Test
  @SneakyThrows
  @DisplayName("param annotated correctly - new style")
  void paramAnnotatedCorrectlyNewStyleTest() {
    // given
    val setS = OnParamNewStyleExample.class
        .getMethod("setS", String.class);

    // when
    assertThat(setS.getParameters()[0])
        // then
        .isNotNull()
        .extracting(param -> param
            .getAnnotation(MyAnno.class))
        .isInstanceOf(MyAnno.class);
  }
}
