package ru.vlapin.demo.lombokdemo.stable.tostring.exclude.conf;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * Person5Test.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class Person3Test {

  @Test
  @DisplayName("ToString.Include annotation with lombok.config param works correctly")
  void toStringIncludeAnnotationWithLombokConfigParamWorksCorrectlyTest() {
    assertThat(Person5.class)
        .hasDeclaredFields("id", "dob");

    // А если несмотря на наличие полей...
    val person5 = new Person5(1L,
        "Вася",
        "Пупкин",
        LocalDate.MIN);

    // ...нужно исключить их из "toString"?
    assertThat(person5)
        .hasToString(
            "Person5(" +
                "firstName=Вася, " +
                "lastName=Пупкин)");
  }
}
