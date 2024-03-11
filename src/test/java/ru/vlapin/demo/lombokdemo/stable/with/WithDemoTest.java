package ru.vlapin.demo.lombokdemo.stable.with;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WithDemoTest {

  @Test
  @DisplayName("With annotation works correctly")
  void withAnnotationWorksCorrectlyTest() {
    // given
    val srcObj = new WithDemo(1, "lorem", true);

    // when
    assertThat(srcObj.withX(2))
        // then
        .isNotNull()
        .isNotSameAs(srcObj)
        .extracting("x", "s", "b")
        .contains(2, "lorem", true);
  }
}
