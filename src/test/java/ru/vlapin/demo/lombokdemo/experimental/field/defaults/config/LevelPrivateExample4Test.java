package ru.vlapin.demo.lombokdemo.experimental.field.defaults.config;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.common.TestUtils.*;

import java.lang.reflect.Modifier;
import java.util.function.IntPredicate;
import lombok.val;
import org.junit.jupiter.api.Disabled;
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
  @Disabled
  @SuppressWarnings("CommentedOutCode")
  @DisplayName("fields default works correctly for package-private")
  void fieldsDefaultWorksCorrectlyForPackagePrivateTest() {
    // given
//    IntPredicate isPackagePrivate = //x ->
//        !Modifier.isPrivate(x)
//        && !Modifier.isPublic(x)
//        && !Modifier.isProtected(x);

    // given
    val isPackagePrivate =
        ((IntPredicate) Modifier::isPrivate)
            .or(Modifier::isProtected)
            .or(Modifier::isPublic)
            .negate();

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
