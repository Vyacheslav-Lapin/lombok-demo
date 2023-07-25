package ru.vlapin.demo.lombokdemo.stable.equalshashcode.exclude;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

/**
 * Person3Test.
 *
 * @author Vyacheslav Lapin
 */
class Person3Test {

  @Test
  @SneakyThrows
  @DisplayName("EqualsAndHashcode Include annotation works correctly")
  void equalsAndHashcodeIncludeAnnotationWorksCorrectlyTest() {
    val example1 = new Person3(1L, "Вася", "Пупкин", LocalDate.now());
    val example2 = new Person3(2L, "Вася", "Пупкин", LocalDate.MAX);
    assertThat(example1).isNotNull()
        .isEqualTo(example2);
  }
}
