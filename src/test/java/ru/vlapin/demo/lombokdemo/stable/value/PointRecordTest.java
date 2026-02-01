package ru.vlapin.demo.lombokdemo.stable.value;

import static org.assertj.core.api.Assertions.*;

import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * PointRecordTest.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
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
