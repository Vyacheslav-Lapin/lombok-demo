package ru.vlapin.demo.lombokdemo.common;

import static org.assertj.core.api.Assertions.*;

import lombok.experimental.ExtensionMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;


@FunctionalInterface
interface TestFuncInt {
  String hello(String name);
}

@ExtensionMethod(value = ReflectionUtils.class,
                 suppressBaseMethods = false)
@DisplayNameGeneration(ReplaceCamelCase.class)
class ReflectionUtilsTest {

  @Test
  @DisplayName("ReflectionUtils.newProxyInstance works correctly")
  void reflectionUtilsNewProxyInstanceWorksCorrectlyTest() {

    // given
    TestFuncInt realObj = name -> String.format("Hello, %s!", name);
    TestFuncInt proxyObj = TestFuncInt.class.newProxyInstance(
        (_, _, args) -> realObj.hello((String) args[0]) + " From Sarah with love…");

    // when
    assertThat(proxyObj)
        .isNotNull()
        // then
        .extracting(po -> po.hello("Duke"))
        .isEqualTo("Hello, Duke! From Sarah with love…");
  }
}
