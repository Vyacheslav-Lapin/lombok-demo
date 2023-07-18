package ru.vlapin.demo.lombokdemo.stable.equalshashcode.exclude;

import java.time.LocalDate;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * EqualsAndHashCodeExcludeAnnotationExampleTest.
 *
 * @author Vyacheslav Lapin
 */
class Person4Test {

  @Test
  @SneakyThrows
  @DisplayName("equalsAndHashcode annotation works correctly")
  void equalsAndHashcodeAnnotationWorksCorrectlyTest() {
    val example1 = new Person4(1L, "Вася", "Пупкин", LocalDate.now());
    val example2 = new Person4(2L, "Вася", "Пупкин", LocalDate.MAX);
    assertThat(example1).isNotNull()
        .isEqualTo(example2);
  }
}
