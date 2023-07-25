package ru.vlapin.demo.lombokdemo.experimental.field.defaults.config;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils;

import java.lang.reflect.Modifier;
import java.util.function.IntPredicate;

import static org.assertj.core.api.Assertions.*;

/**
 * LevelPrivateExample4Test.
 */
@SuppressWarnings("java:S1607")
class LevelPrivateExample4Test {

  @Test
  @Disabled
  @DisplayName("fields default works correctly for package-private")
  void fieldsDefaultWorksCorrectlyForPackagePrivateTest() {
    // given
    IntPredicate isPackagePrivate = //x ->
//        !Modifier.isPrivate(x)
//        && !Modifier.isPublic(x)
//        && !Modifier.isProtected(x);
        ((IntPredicate) Modifier::isPrivate)
            .or(Modifier::isProtected)
            .or(Modifier::isPublic)
            .negate();

    // when
    assertThat(LevelPrivateExample4.class)
        // then
        .hasOnlyDeclaredFields("x", "y", "z", "s")
        .matches(aClass -> TestUtils.checkFieldModifier(aClass, "x", Modifier::isPrivate))
        .matches(aClass -> TestUtils.checkFieldModifier(aClass, "y", Modifier::isProtected))
        .matches(aClass -> TestUtils.checkFieldModifier(aClass, "z", Modifier::isPublic))
        .matches(aClass -> TestUtils.checkFieldModifier(aClass, "s", isPackagePrivate));
  }
}
