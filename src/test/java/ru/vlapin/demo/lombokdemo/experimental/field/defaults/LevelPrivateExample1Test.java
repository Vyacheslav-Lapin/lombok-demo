package ru.vlapin.demo.lombokdemo.experimental.field.defaults;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

@DisplayNameGeneration(ReplaceCamelCase.class)
class LevelPrivateExample1Test {

  @Test
//  @DisplayName("private modifier sets correctly")
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
