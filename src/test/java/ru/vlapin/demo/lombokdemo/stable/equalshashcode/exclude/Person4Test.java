package ru.vlapin.demo.lombokdemo.stable.equalshashcode.exclude;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * EqualsAndHashCodeExcludeAnnotationExampleTest.
 *
 * @author Vyacheslav Lapin
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
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
