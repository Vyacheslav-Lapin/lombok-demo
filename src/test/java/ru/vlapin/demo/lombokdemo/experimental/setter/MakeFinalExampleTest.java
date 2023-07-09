package ru.vlapin.demo.lombokdemo.experimental.setter;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * MakeFinalExampleTest.
 */
class MakeFinalExampleTest {

  @Test
  @SneakyThrows
  @DisplayName("makeFinal param works correctly")
  void makeFinalParamWorksCorrectlyTest() {
    val getX = MakeFinalExample.class
        .getMethod("getX");
    val setX = MakeFinalExample.class
        .getMethod("setX", int.class);
    Method[] methods = {getX, setX};

    assertThat(methods).isNotNull()
        .isNotEmpty()
        .extracting(Method::getModifiers)
        .allMatch(Modifier::isFinal);
  }
}
