package ru.vlapin.demo.lombokdemo.experimental.field.defaults.config.conf;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.function.IntPredicate;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.experimental.field.defaults.config.LevelPrivateExample3Test.*;

/**
 * MakeFinalExample3Test.
 */
class MakeFinalExample3Test {

  @Test
  @DisplayName("NonFinal annotation works correctly")
  void nonFinalAnnotationWorksCorrectlyTest() {
    // given
    val isNotFinal = ((IntPredicate) Modifier::isFinal).negate();

    // when
    assertThat(MakeFinalExample3.class)
        // then
        .hasOnlyDeclaredFields("x", "y", "s")
        .matches(aClass -> checkFieldModifier(aClass, "x", isNotFinal))
        .matches(aClass -> checkFieldModifier(aClass, "y", Modifier::isFinal))
        .matches(aClass -> checkFieldModifier(aClass, "s", Modifier::isFinal));
  }
}
