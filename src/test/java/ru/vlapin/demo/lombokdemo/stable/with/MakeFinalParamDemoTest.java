package ru.vlapin.demo.lombokdemo.stable.with;

import static org.assertj.core.api.Assertions.*;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MakeFinalParamDemoTest {

  @Test
  @SneakyThrows
  @DisplayName("With annotation makeFinal param works correctly")
  void withAnnotationMakeFinalParamWorksCorrectlyTest() {
    // given
    val withX = MakeFinalParamDemo.class
        .getDeclaredMethod("withX", int.class);

    // when
    assertThat(withX)
        // then
        .isNotNull()
        .extracting(Method::getModifiers)
        .matches(Modifier::isFinal);
  }
}
