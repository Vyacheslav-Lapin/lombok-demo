package ru.vlapin.demo.lombokdemo.stable.builder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BuilderBeforeClassDemoTests {

  @Test
  @DisplayName("@Builder before class works correctly")
  void builderBeforeClassWorksCorrectlyTest() {
    assertThat(BuilderBeforeClassDemo.builder()
                   .s("lorem")
                   .x(1)
                   .b(true).build()).isNotNull()
        .extracting("x", "s", "b")
        .contains(1, "lorem", true);
  }
}
