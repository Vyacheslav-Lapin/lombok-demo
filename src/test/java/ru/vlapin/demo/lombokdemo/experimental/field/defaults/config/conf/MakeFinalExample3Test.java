package ru.vlapin.demo.lombokdemo.experimental.field.defaults.config.conf;

import lombok.val;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils;

import java.lang.reflect.Modifier;
import java.util.function.IntPredicate;

import static org.assertj.core.api.Assertions.*;

/**
 * MakeFinalExample3Test.
 */
@SuppressWarnings("java:S1607")
class MakeFinalExample3Test {

  @Test
  @Disabled
  @DisplayName("NonFinal annotation works correctly")
  void nonFinalAnnotationWorksCorrectlyTest() {
    // given
    val isNotFinal = ((IntPredicate) Modifier::isFinal).negate();

    // when
    assertThat(MakeFinalExample3.class)
        // then
        .hasOnlyDeclaredFields("x", "y", "s")
        .matches(aClass -> TestUtils.checkFieldModifier(aClass, "x", isNotFinal))
        .matches(aClass -> TestUtils.checkFieldModifier(aClass, "y", Modifier::isFinal))
        .matches(aClass -> TestUtils.checkFieldModifier(aClass, "s", Modifier::isFinal));
  }
}
