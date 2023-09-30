package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

import static org.assertj.core.api.Assertions.*;


@DisplayNameGeneration(ReplaceCamelCase.class)
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
  @DisplayName("getSortedArray method works correctly")
  void getSortedArrayMethodWorksCorrectlyTest() {
    assertThat(new LombokExtensionMethodExample()
                   .getSortedArray()).isNotNull()
        .containsExactly(2, 3, 5, 8);
  }

  @Test
  @SneakyThrows
//  @DisplayName("format String method works correctly as a extension method")
  void formatStringMethodWorksCorrectlyAsAExtensionMethodTest() {
    assertThat(new LombokExtensionMethodExample().hw(5)).isNotNull()
        .isEqualTo("Hello, 5 World!");
  }
}
