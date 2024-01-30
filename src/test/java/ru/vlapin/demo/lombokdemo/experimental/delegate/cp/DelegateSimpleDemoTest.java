package ru.vlapin.demo.lombokdemo.experimental.delegate.cp;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DelegateSimpleDemoTest {

  @Test
  @DisplayName("Delegate annotation works correctly")
  void delegateAnnotationWorksCorrectlyTest() {
    // given
    val usageProblemDemo = new DelegateSimpleDemo(5);

    // when
    assertThat(usageProblemDemo).isNotNull()
        // then
        .extracting(
            DelegateSimpleDemo::method,
            DelegateSimpleDemo::x)
        .contains("x = 5", 5);
  }
}
