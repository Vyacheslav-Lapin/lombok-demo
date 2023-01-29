package ru.vlapin.demo.lombokdemo.stable.equalshashcode.exclude;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * EqualsAndHashCodeExcludeAnnotationExampleTest.
 *
 * @author Vyacheslav Lapin
 */
class EqualsAndHashCodeExcludeAnnotationExampleTest {

  @Test
  @SneakyThrows
  @DisplayName("equalsAndHashcode annotation works correctly")
  void equalsAndHashcodeAnnotationWorksCorrectlyTest() {
    val example1 = new EqualsAndHashCodeExcludeAnnotationExample(1L, "Вася", "Пупкин", LocalDate.now());
    val example2 = new EqualsAndHashCodeExcludeAnnotationExample(2L, "Вася", "Пупкин", LocalDate.MAX);
    assertThat(example1).isNotNull()
        .isEqualTo(example2);

  }
}
