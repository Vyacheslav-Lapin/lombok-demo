package ru.vlapin.demo.lombokdemo.stable.builder;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BuilderDefaultSimpleDemoTests {

  @Test
  @DisplayName("@Builder.Default works correctly")
  void builderDefaultWorksCorrectlyTest() {

    val builderDefaultSimpleDemo = BuilderDefaultSimpleDemo.builder().build();
    assertThat(builderDefaultSimpleDemo).isNotNull()
        .extracting("x")
        .isEqualTo(7);

    for (int x : new int[] {1, 2, 3})
      assertThat(BuilderDefaultSimpleDemo.builder().x(x).build()).isNotNull()
          .extracting("x")
          .isEqualTo(x);
  }
}
