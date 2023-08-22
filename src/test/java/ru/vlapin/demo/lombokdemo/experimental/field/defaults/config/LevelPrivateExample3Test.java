package ru.vlapin.demo.lombokdemo.experimental.field.defaults.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.common.TestUtils.*;

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
        .matches(fieldModifierCheck("x", Modifier::isPrivate))
        .matches(fieldModifierCheck("y", Modifier::isProtected))
        .matches(fieldModifierCheck("z", Modifier::isPublic));
  }
}
