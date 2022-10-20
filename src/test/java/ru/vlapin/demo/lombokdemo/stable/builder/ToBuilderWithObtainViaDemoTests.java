package ru.vlapin.demo.lombokdemo.stable.builder;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.stable.builder.ToBuilderWithObtainViaDemo.*;

class ToBuilderWithObtainViaDemoTests {

  @Test
  @SneakyThrows
  @DisplayName("@Builder with ObtainVia field annotation works correctly")
  void builderWithObtainViaFieldAnnotationWorksCorrectlyTest() {
    // Как внести изменения в поле при вызове toBuilder?
    val obj = builder().s("lorem").build();
    assertThat(obj.toBuilder().build()).isNotNull()
        .extracting("s")
        .isEqualTo("lorem copy");
  }
}
