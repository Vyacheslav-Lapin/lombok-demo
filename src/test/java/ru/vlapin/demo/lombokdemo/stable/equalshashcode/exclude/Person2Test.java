package ru.vlapin.demo.lombokdemo.stable.equalshashcode.exclude;

import java.time.LocalDate;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * EqualsAndHashCodeOfAnnotationParamExampleTest.
 *
 * @author Vyacheslav Lapin
 */
class Person2Test {

  @Test
  @SneakyThrows
  @DisplayName("EqualsAndHashCode with 'of' annotation param works correctly")
  void equalsAndHashCodeWithOfAnnotationParamWorksCorrectlyTest() {
    val example1 = new Person2(1L, "Вася", "Пупкин", LocalDate.now());
    val example2 = new Person2(2L, "Вася", "Пупкин", LocalDate.MAX);
    assertThat(example1).isNotNull()
        .isEqualTo(example2);
  }
}
