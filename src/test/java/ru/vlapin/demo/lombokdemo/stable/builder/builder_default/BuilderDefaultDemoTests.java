package ru.vlapin.demo.lombokdemo.stable.builder.builder_default;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BuilderDefaultDemoTests {

  @Test
  @DisplayName("@Builder.Default works correctly")
  void builderDefaultWorksCorrectlyTest() {

    val builderDefaultSimpleDemo = BuilderDefaultDemo.builder().build();
    assertThat(builderDefaultSimpleDemo).isNotNull()
        .extracting("x")
        .isEqualTo(7);

    for (int x : new int[]{1, 2, 3})
      assertThat(BuilderDefaultDemo.builder().x(x).build()).isNotNull()
                                                           .extracting("x")
                                                           .isEqualTo(x);
  }
}
