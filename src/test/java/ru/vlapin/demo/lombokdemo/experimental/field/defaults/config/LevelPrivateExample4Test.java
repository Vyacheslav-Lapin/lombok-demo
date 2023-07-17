package ru.vlapin.demo.lombokdemo.experimental.field.defaults.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.function.IntPredicate;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.experimental.field.defaults.config.LevelPrivateExample3Test.*;

/**
 * LevelPrivateExample4Test.
 */
class LevelPrivateExample4Test {

  @Test
  @DisplayName("fields default works correctly for package-private")
  void fieldsDefaultWorksCorrectlyForPackagePrivateTest() {
    // given
    IntPredicate isPackagePrivate =
        ((IntPredicate) Modifier::isPrivate)
            .or(Modifier::isProtected)
            .or(Modifier::isPublic)
            .negate();

    // when
    assertThat(LevelPrivateExample4.class)
        // then
        .hasOnlyDeclaredFields("x", "y", "z", "s")
        .matches(aClass -> checkFieldModifier(aClass, "x", Modifier::isPrivate))
        .matches(aClass -> checkFieldModifier(aClass, "y", Modifier::isProtected))
        .matches(aClass -> checkFieldModifier(aClass, "z", Modifier::isPublic))
        .matches(aClass -> checkFieldModifier(aClass, "s", isPackagePrivate));
  }
}
