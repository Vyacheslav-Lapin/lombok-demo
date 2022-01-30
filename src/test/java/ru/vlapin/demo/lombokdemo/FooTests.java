package ru.vlapin.demo.lombokdemo;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class FooTests {

  @Test
  @SneakyThrows
  @DisplayName("Foo constructor works correctly")
  void fooConstructorWorksCorrectlyTest() {
    assertThat(new Foo("Lorem ipsum dolor sit amet", true)).isNotNull()
        .extracting(Foo::getS, Foo::isB)
        .contains("Lorem ipsum dolor sit amet", true);
  }
}
