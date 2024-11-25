package ru.vlapin.demo.lombokdemo.stable.builder.dsl;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Person0Test {

  @Test
  @DisplayName("Builder works correctly")
  void builderWorksCorrectlyTest() {
    // given
    val vasilisaPupkina = Person0.builder()
                                 .firstName("Василиса")
                                 .lastName("Пупкина")
                                 .age(21)
                                 .isMale(false)
                                 .isMarried(false).build();
    // when
    assertThat(vasilisaPupkina).isNotNull()
        // then
        .extracting(
            Person0::firstName,
            Person0::lastName,
            Person0::age,
            Person0::isMale,
            Person0::isMarried)
        .contains("Василиса", "Пупкина", 21, false, false);
  }
}
