package ru.vlapin.demo.lombokdemo.stable.tostring.exclude;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

/**
 * Person1Test.
 */
class Person1Test {

  @Test
  @DisplayName("ToString exclude param works correctly")
  void toStringExcludeParamWorksCorrectlyTest() {

    // А если несмотря на наличие полей...
    assertThat(Person1.class)
        .hasDeclaredFields("id", "dob");

    val person1 = new Person1(1L,
        "Вася",
        "Пупкин",
        LocalDate.MIN);

    // ...нужно исключить их из "toString"?
    assertThat(person1)
        .hasToString(
            "Person1(" +
                "firstName=Вася, " +
                "lastName=Пупкин)");
  }
}
