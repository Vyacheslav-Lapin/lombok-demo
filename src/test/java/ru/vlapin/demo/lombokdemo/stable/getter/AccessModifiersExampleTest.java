package ru.vlapin.demo.lombokdemo.stable.getter;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.*;

/**
 * Сгенерированный метод getter/setter будет иметь модификатор доступа <code>public</code>,
 * если вы явно не укажете AccessLevel, как показано в примере ниже. Приемлемыми уровнями
 * доступа являются: PUBLIC, PROTECTED, PACKAGE и PRIVATE.
 */
class AccessModifiersExampleTest {
  @Test
  @SneakyThrows
  @DisplayName("value parameter of getter annotation works correctly")
  void valueParameterOfGetterAnnotationWorksCorrectlyTest() {
    // given
    val amem = AccessModifiersExample.class
        .getDeclaredMethod("getX");

    // when
    assertThat(amem).isNotNull()
        // then
        .extracting(Method::getModifiers)
        .matches(Modifier::isProtected);
  }
}
