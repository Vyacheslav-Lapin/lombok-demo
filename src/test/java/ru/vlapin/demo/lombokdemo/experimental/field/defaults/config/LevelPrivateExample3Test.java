package ru.vlapin.demo.lombokdemo.experimental.field.defaults.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils;

import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.*;

/**
 * LevelPrivateExample3Test.
 */
class LevelPrivateExample3Test {

  @Test
  @DisplayName("fields default works correctly")
  void fieldsDefaultWorksCorrectlyTest() {
    // when
    assertThat(LevelPrivateExample3.class)
        // then
        .hasOnlyDeclaredFields("x", "y", "z")
        .matches(aClass -> TestUtils.checkFieldModifier(aClass, "x", Modifier::isPrivate))
        .matches(aClass -> TestUtils.checkFieldModifier(aClass, "y", Modifier::isProtected))
        .matches(aClass -> TestUtils.checkFieldModifier(aClass, "z", Modifier::isPublic));
  }
}
