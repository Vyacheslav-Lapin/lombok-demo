package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LombokExtensionMethodExampleTests {

  @Test
  @SneakyThrows
  @DisplayName("UseExtensionMethods method works correctly")
  void useExtensionMethodsMethodWorksCorrectlyTest() {
    assertThat(new LombokExtensionMethodExample()
                   .useExtensionMethods()).isNotNull()
        .isEqualTo("Hello, world!");
  }

  @Test
  void getSortedArray() {
    assertThat(new LombokExtensionMethodExample()
                   .getSortedArray()).isNotNull()
        .containsExactly(2, 3, 5, 8);
  }

  @Test
  @SneakyThrows
  @DisplayName("format String method works correctly as a extention method")
  void formatStringMethodWorksCorrectlyAsAExtentionMethodTest() {
    assertThat(new LombokExtensionMethodExample().hw(5)).isNotNull()
        .isEqualTo("Hello, 5 World!");
  }
}
