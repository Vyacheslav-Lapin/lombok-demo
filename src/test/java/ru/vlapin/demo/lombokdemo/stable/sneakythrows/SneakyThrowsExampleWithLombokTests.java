package ru.vlapin.demo.lombokdemo.stable.sneakythrows;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.nio.charset.StandardCharsets.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class SneakyThrowsExampleWithLombokTests {

  @Test
  @SneakyThrows
  @DisplayName("Utf8ToString method works correctly")
  void utf8ToStringMethodWorksCorrectlyTest() {
    assertThat(new SneakyThrowsExampleWithLombok()
                   .utf8ToString(
                       "Lorem ipsum dolor sit amet".getBytes(UTF_8))).isNotNull()
        .isEqualTo("Lorem ipsum dolor sit amet");
  }

  @Test
  @DisplayName("Run method works correctly")
  void runMethodWorksCorrectlyTest() {
    assertThrows(Throwable.class,
                 () -> new SneakyThrowsExampleWithLombok().run());
  }
}
