package ru.vlapin.demo.lombokdemo.stable.tostring.exclude;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

/**
 * Person3Test.
 */
class Person3Test {
  @Test
  @DisplayName("TuString.Exclude annotation works correctly")
  void tuStringExcludeAnnotationWorksCorrectlyTest() {
    assertThat(Person3.class)
        .hasDeclaredFields("id", "dob");

    // А если несмотря на наличие полей...
    val person3 = new Person3(1L,
        "Вася",
        "Пупкин",
        LocalDate.MIN);

    // ...нужно исключить их из "toString"?
    assertThat(person3)
        .hasToString(
            "Person3(" +
                "firstName=Вася, " +
                "lastName=Пупкин)");
  }
}
