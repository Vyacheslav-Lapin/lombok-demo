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
 * Person3Test.
 *
 * @author Vyacheslav Lapin
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
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
