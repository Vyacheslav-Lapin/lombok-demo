package ru.vlapin.demo.lombokdemo.stable.tostring.exclude;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * Person1Test.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class Person1Test {

  @Test
  @DisplayName("@ToString exclude param works correctly")
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
