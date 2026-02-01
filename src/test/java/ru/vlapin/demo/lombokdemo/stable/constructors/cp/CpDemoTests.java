package ru.vlapin.demo.lombokdemo.stable.constructors.cp;

import static org.assertj.core.api.Assertions.*;

import java.beans.ConstructorProperties;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import lombok.experimental.ExtensionMethod;
import lombok.val;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@ExtensionMethod(
    value = Arrays.class,
    suppressBaseMethods = false)
@DisplayNameGeneration(ReplaceCamelCase.class)
class CpDemoTests {

  @Test
  @Disabled("Another config (see comment below)")
  @DisplayName("Constructor param names looses without '-parameters' compiler flag")
  void constructorParamNamesLoosesWithoutParametersCompilerFlagTest() {

    // Note! The test will fail if the compiler receives the "-parameters" flag - in
    // this case, the parameter names are stored in byte-code. Spring Boot project
    // usually sets this flag, so if the Spring Boot project is a standard project,
    // the test will not work out of the box. To make it work in a Spring Boot
    // project, you need to explicitly set the maven.compiler.parameters property
    // to false.

    // given
    val bytecodeParamNames = CpDemo.class
        .getConstructors()[0]
        .getParameters().stream()
        .map(Parameter::getName)
        .toArray(String[]::new);

    // when
    assertThat(bytecodeParamNames)
        // then
        .contains("arg0", "arg1", "arg2");

  }

  @Test
  @DisplayName("ConstructorProperties set correctly")
  void constructorPropertiesSetCorrectlyTest() {
    val paramNames = CpDemo.class
        .getConstructors()[0]
        .getDeclaredAnnotation(
            ConstructorProperties.class)
        .value();

    // when
    assertThat(paramNames).isNotNull()
        // then
        .contains("x", "s", "b");
  }
}
