package ru.vlapin.demo.lombokdemo.experimental.utilityclass;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.experimental.utilityclass.UtilityClassExample.*;

class UtilityClassExampleTests {

  @Test
  @SneakyThrows
  @DisplayName("UtilityClass lombok annotation works correctly")
  void utilityClassLombokAnnotationWorksCorrectlyTest() {
    assertThat(UtilityClassExample.class).isFinal();
    assertThat(addSomething(1)).isEqualTo(6);
  }
}
