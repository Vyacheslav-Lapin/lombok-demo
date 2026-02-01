package ru.vlapin.demo.lombokdemo.stable.tostring.exclude;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * Person3Test.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class Person3Test {
  @Test
  @DisplayName("ToString.Exclude annotation works correctly")
  void toStringExcludeAnnotationWorksCorrectlyTest() {
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
