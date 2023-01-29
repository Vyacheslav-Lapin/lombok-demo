package ru.vlapin.demo.lombokdemo.experimental.helper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HelperExampleTest {

  @Test
  @DisplayName("Helper lombok annotation works correctly")
  void helperLombokAnnotationWorksCorrectlyTest() {
    assertThat(new HelperExample().someMethod(5))
        .isEqualTo(20);
  }
}
