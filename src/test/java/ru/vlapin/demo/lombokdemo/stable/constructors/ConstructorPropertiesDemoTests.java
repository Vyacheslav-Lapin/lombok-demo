package ru.vlapin.demo.lombokdemo.stable.constructors;

import java.beans.ConstructorProperties;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConstructorPropertiesDemoTests {

  @Test
  @SneakyThrows
  @DisplayName("ConstructorProperties setted correctly")
  void constructorPropertiesSettedCorrectlyTest() {
    val paramNames = ConstructorPropertiesDemo.class
                         .getConstructors()[0]
                         .getDeclaredAnnotation(ConstructorProperties.class)
                         .value();

    assertThat(String.join(", ", paramNames))
        .isEqualTo("x, s, z");
  }
}
