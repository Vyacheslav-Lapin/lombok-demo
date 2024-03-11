package ru.vlapin.demo.lombokdemo.stable.nullability.record;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NonNullRecordDemoTest {

  @Test
  @DisplayName("NonNull annotation before record component works correctly")
  void nonNullAnnotationBeforeRecordComponentWorksCorrectlyTest() {
    //noinspection DataFlowIssue
    assertThatExceptionOfType(NullPointerException.class)
        .isThrownBy(() -> new NonNullRecordDemo(null))
        .withMessage("x is marked non-null but is null");
  }
}
