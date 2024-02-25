package ru.vlapin.demo.lombokdemo.stable.builder;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BuilderBeforeClassDemoTests {

  @Test
  @DisplayName("@Builder before class works correctly")
  void builderBeforeClassWorksCorrectlyTest() {
    val demo =
        BuilderBeforeClassDemo
            .builder()
            .s("lorem")
            .x(1)
            .b(true).build();
    assertThat(demo)
        .isNotNull()
        .extracting("x", "s", "b")
        .contains(1, "lorem", true);
  }
}
