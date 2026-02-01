package ru.vlapin.demo.lombokdemo.stable.builder;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.stable.builder.ToBuilderWithObtainViaDemo.*;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
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
