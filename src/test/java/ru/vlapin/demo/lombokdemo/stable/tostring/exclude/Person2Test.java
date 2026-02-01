package ru.vlapin.demo.lombokdemo.stable.tostring.exclude;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * Person2Test.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class Person2Test {
  @Test
  @DisplayName("ToString of param works correctly")
  void toStringOfParamWorksCorrectlyTest() {
    assertThat(Person2.class)
        .hasDeclaredFields("id", "dob");

    // А если несмотря на наличие полей...
    val person2 = new Person2(1L,
        "Вася",
        "Пупкин",
        LocalDate.MIN);

    // ...нужно исключить их из "toString"?
    assertThat(person2)
        .hasToString(
            "Person2(" +
                "firstName=Вася, " +
                "lastName=Пупкин)");
  }
}
