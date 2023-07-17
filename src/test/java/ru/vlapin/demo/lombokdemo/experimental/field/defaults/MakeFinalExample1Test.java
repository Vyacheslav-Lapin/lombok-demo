package ru.vlapin.demo.lombokdemo.experimental.field.defaults;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

/**
 * MakeFinalExample1Test.
 */
class MakeFinalExample1Test {

  @Test
  @DisplayName("makeFinal param works correctly")
  void makeFinalParamWorksCorrectlyTest() {
    // when
    assertThat(MakeFinalExample1.class)
        // then
        .isNotNull()
        .hasOnlyDeclaredFields("x", "s")
        .matches(aClass -> Arrays.stream(
                aClass.getDeclaredFields())
            .map(Field::getModifiers)
            .allMatch(Modifier::isFinal));
  }
}
