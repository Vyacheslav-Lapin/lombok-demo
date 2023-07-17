package ru.vlapin.demo.lombokdemo.experimental.field.defaults.config;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.function.IntPredicate;

import static org.assertj.core.api.Assertions.*;

/**
 * LevelPrivateExample3Test.
 */
public class LevelPrivateExample3Test {

  @SneakyThrows
  public static boolean checkFieldModifier(Class<?> aClass,
                                           String field,
                                           IntPredicate check) {
    return check.test(
        aClass.getDeclaredField(field)
            .getModifiers());
  }

  @Test
  @DisplayName("fields default works correctly")
  void fieldsDefaultWorksCorrectlyTest() {
    // when
    assertThat(LevelPrivateExample3.class)
        // then
        .hasOnlyDeclaredFields("x", "y", "z")
        .matches(aClass -> checkFieldModifier(aClass, "x", Modifier::isPrivate))
        .matches(aClass -> checkFieldModifier(aClass, "y", Modifier::isProtected))
        .matches(aClass -> checkFieldModifier(aClass, "z", Modifier::isPublic));
  }
}
