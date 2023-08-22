package ru.vlapin.demo.lombokdemo.stable.constructors;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * NacDemoTest.
 */
class NacDemo1Test {

  @Test
  @DisplayName("NoArgsConstructor annotation works correctly")
  void noArgsConstructorAnnotationWorksCorrectlyTest() {
    // given
    val nacDemo = new NacDemo1();

    // when
    assertThat(nacDemo).isNotNull()
        // then
        .extracting("x", "s", "z")
        .contains(0, null, false);
  }
}
