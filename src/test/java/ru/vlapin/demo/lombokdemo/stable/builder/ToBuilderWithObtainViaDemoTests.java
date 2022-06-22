package ru.vlapin.demo.lombokdemo.stable.builder;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ToBuilderWithObtainViaDemoTests {

  @Test
  @SneakyThrows
  @DisplayName("@Builder with ObtainVia field annotation works correctly")
  void builderWithObtainViaFieldAnnotationWorksCorrectlyTest() {
    // Как внести изменения в поле при вызове toBuilder?
    assertThat(ToBuilderWithObtainViaDemo.builder()
                   .s("lorem").build()
                   .toBuilder().build()).isNotNull()
        .extracting("s")
        .isEqualTo("lorem copy");
  }
}
