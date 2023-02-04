package ru.vlapin.demo.lombokdemo.stable.equalshashcode.exclude;

import java.time.LocalDate;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * EqualsAndHashCodeExcludeAnnotationParamExampleTest.
 *
 * @author Vyacheslav Lapin
 */
class EqualsAndHashCodeExcludeAnnotationParamExampleTest {

  @Test
  @DisplayName("EqualsAndHashCode with exclude annotation param works correctly")
  void equalsAndHashCodeWithExcludeAnnotationParamWorksCorrectlyTest() {
    val example1 = new EqualsAndHashCodeExcludeAnnotationParamExample(1L, "Вася", "Пупкин", LocalDate.now());
    val example2 = new EqualsAndHashCodeExcludeAnnotationParamExample(2L, "Вася", "Пупкин", LocalDate.MAX);
    assertThat(example1).isNotNull()
        .isEqualTo(example2);
  }
}
