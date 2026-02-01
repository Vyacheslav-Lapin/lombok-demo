package ru.vlapin.demo.lombokdemo.experimental.accessors;

import static org.assertj.core.api.Assertions.*;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * MakeFinalExampleTest.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class MakeFinalAccessorsExampleTest {

  @Test
  @SneakyThrows
  @DisplayName("makeFinal param works correctly")
  void makeFinalParamWorksCorrectlyTest() {
    // given
    val aClass = MakeFinalAccessorsExample.class;
    val getX = aClass.getMethod("getX");
    val getS = aClass.getMethod("getS");
    val setX = aClass.getMethod("setX", int.class);
    val withS = aClass.getMethod("withS", String.class);
    Method[] methods = {getX, setX, getS, withS};

    // when
    assertThat(methods)
        // then
        .isNotNull()
        .isNotEmpty()
        .extracting(Method::getModifiers)
        .allMatch(Modifier::isFinal);
  }
}
