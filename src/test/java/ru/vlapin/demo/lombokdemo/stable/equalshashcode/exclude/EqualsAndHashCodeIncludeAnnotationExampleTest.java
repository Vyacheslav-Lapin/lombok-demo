package ru.vlapin.demo.lombokdemo.stable.equalshashcode.exclude;

import java.time.LocalDate;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * EqualsAndHashCodeIncludeAnnotationExampleTest.
 *
 * @author Vyacheslav Lapin
 */
class EqualsAndHashCodeIncludeAnnotationExampleTest {

  @Test
  @SneakyThrows
  @DisplayName("EqualsAndHashcode Include annotation works correctly")
  void equalsAndHashcodeIncludeAnnotationWorksCorrectlyTest() {
    val example1 = new EqualsAndHashCodeIncludeAnnotationExample(1L, "Вася", "Пупкин", LocalDate.now());
    val example2 = new EqualsAndHashCodeIncludeAnnotationExample(2L, "Вася", "Пупкин", LocalDate.MAX);
    assertThat(example1).isNotNull()
        .isEqualTo(example2);
  }
}
