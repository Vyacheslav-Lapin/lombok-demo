package ru.vlapin.demo.lombokdemo.stable.value;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;

import static org.assertj.core.api.Assertions.*;

/**
 * PointRecordTest.
 */
class PointRecordTest {

  @Test
  @DisplayName("Record components extracted correctly")
  void recordComponentsExtractedCorrectlyTest() {
    // given
    RecordComponent[] components =
        PointRecord.class.getRecordComponents();

    // when
    assertThat(components)
        // then
        .isNotNull()
        .hasSize(2)
        .extracting(RecordComponent::getAccessor)
        .extracting(Method::getName)
        .contains("x", "y");

  }
}
