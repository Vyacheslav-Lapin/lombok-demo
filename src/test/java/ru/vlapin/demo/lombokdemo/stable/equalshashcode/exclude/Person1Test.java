package ru.vlapin.demo.lombokdemo.stable.equalshashcode.exclude;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

/**
 * EqualsAndHashCodeExcludeAnnotationParamExampleTest.
 *
 * @author Vyacheslav Lapin
 */
class Person1Test {

  @Test
  @DisplayName("EqualsAndHashCode with exclude annotation param works correctly")
  void equalsAndHashCodeWithExcludeAnnotationParamWorksCorrectlyTest() {
    val example1 = new Person1(1L,
        "Вася",
        "Пупкин",
        LocalDate.now());
    val example2 = new Person1(2L,
        "Вася",
        "Пупкин",
        LocalDate.MAX);
    assertThat(example1)
        .isEqualTo(example2);
  }
}
