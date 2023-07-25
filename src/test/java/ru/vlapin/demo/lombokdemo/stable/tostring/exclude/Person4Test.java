package ru.vlapin.demo.lombokdemo.stable.tostring.exclude;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

/**
 * Person4Test.
 */
class Person4Test {

  @Test
  @DisplayName("ToString.Include annotation works correctly")
  void toStringIncludeAnnotationWorksCorrectlyTest() {
    assertThat(Person4.class)
        .hasDeclaredFields("id", "dob");

    // А если несмотря на наличие полей...
    val person4 = new Person4(1L,
        "Вася",
        "Пупкин",
        LocalDate.MIN);

    // ...нужно исключить их из "toString"?
    assertThat(person4)
        .hasToString(
            "Person4(" +
                "firstName=Вася, " +
                "lastName=Пупкин)");
  }
}
