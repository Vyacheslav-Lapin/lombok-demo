package ru.vlapin.demo.lombokdemo.stable.builder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BuilderDefaultSimpleDemoTests {

  @Test
  @DisplayName("@Builder.Default works correctly")
  void builderDefaultWorksCorrectlyTest() {

    assertThat(BuilderDefaultSimpleDemo.builder().build()).isNotNull()
        .extracting("x")
        .isEqualTo(7);

    assertThat(BuilderDefaultSimpleDemo.builder().x(5).build()).isNotNull()
        .extracting("x")
        .isEqualTo(5);
  }
}
