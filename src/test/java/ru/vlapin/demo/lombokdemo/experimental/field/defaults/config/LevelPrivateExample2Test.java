package ru.vlapin.demo.lombokdemo.experimental.field.defaults.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.experimental.field.defaults.LevelPrivateExample1;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

/**
 * LevelPrivateExample2Test.
 */
class LevelPrivateExample2Test {
  @Test
  @DisplayName("private modifier sets correctly")
  void privateModifierSetsCorrectlyTest() {
    // when
    assertThat(LevelPrivateExample1.class)
        .hasOnlyDeclaredFields("x", "s")
        .matches(aClass -> Arrays.stream(
                aClass.getDeclaredFields())
            .map(Field::getModifiers)
            .allMatch(Modifier::isPrivate));
  }
}
