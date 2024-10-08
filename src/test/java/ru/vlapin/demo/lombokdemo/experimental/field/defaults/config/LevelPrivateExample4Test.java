package ru.vlapin.demo.lombokdemo.experimental.field.defaults.config;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.common.ReflectionUtils.*;
import static ru.vlapin.demo.lombokdemo.common.TestUtils.*;

import java.lang.reflect.Modifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

/**
 * LevelPrivateExample4Test.
 */
@SuppressWarnings("java:S1607")
@DisplayNameGeneration(ReplaceCamelCase.class)
class LevelPrivateExample4Test {

  @Test
//  @Disabled
  @SuppressWarnings("java:S125")
  @DisplayName("fields default works correctly for package-private")
  void fieldsDefaultWorksCorrectlyForPackagePrivateTest() {
    // when
    assertThat(LevelPrivateExample4.class)
        // then
        .hasOnlyDeclaredFields("x", "y", "z", "s")
        .matches(fieldModifierCheck("x", Modifier::isPrivate))
        .matches(fieldModifierCheck("y", Modifier::isProtected))
        .matches(fieldModifierCheck("z", Modifier::isPublic))
        .matches(fieldModifierCheck("s", isPackagePrivate));
  }
}
