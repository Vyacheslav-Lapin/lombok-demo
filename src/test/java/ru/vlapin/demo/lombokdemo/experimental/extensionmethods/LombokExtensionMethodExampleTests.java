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
}
