package ru.vlapin.demo.lombokdemo.stable.equalshashcode.exclude;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * EqualsAndHashCodeExcludeAnnotationParamExampleTest.
 *
 * @author Vyacheslav Lapin
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
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
