package ru.vlapin.demo.lombokdemo.experimental.setter.annotating;

import static org.assertj.core.api.Assertions.*;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * OnParamOldStyleExampleTest.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
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
