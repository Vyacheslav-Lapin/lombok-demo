package ru.vlapin.demo.lombokdemo.experimental.field.defaults.config.conf;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.common.TestUtils.*;

import java.lang.reflect.Modifier;
import java.util.function.IntPredicate;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

/**
 * MakeFinalExample3Test.
 */
@SuppressWarnings("java:S1607")
@DisplayNameGeneration(ReplaceCamelCase.class)
class MakeFinalExample3Test {

  @Test
//  @Disabled
  @DisplayName("NonFinal annotation works correctly")
  void nonFinalAnnotationWorksCorrectlyTest() {
    // given
    val isNotFinal = ((IntPredicate) Modifier::isFinal).negate();

    // when
    assertThat(MakeFinalExample3.class)
        // then
        .hasOnlyDeclaredFields("x", "y", "s")
        .matches(fieldModifierCheck("x", isNotFinal))
        .matches(fieldModifierCheck("y", Modifier::isFinal))
        .matches(fieldModifierCheck("s", Modifier::isFinal));
  }
}
