package ru.vlapin.demo.lombokdemo.stable.builder.dsl;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Person1Test {

  @Test
  @DisplayName("Builder works correctly")
  void builderWorksCorrectlyTest() {
    // given
    val vasilisaPupkina = Person1.builder(builder -> builder
        .firstName("Василиса")
        .lastName("Пупкина")
        .age(21));
    // when
    assertThat(vasilisaPupkina).isNotNull()
                               // then
                               .extracting(
                                   Person1::firstName,
                                   Person1::lastName,
                                   Person1::age,
                                   Person1::isMale,
                                   Person1::isMarried)
                               .contains("Василиса", "Пупкина", 21, false, false);
  }

  @Test
  @DisplayName("Old builder works correctly")
  void oldBuilderWorksCorrectlyTest() {
    // given
    val vasilisaPupkina = Person1.builder(it -> it
        .firstName("Василиса")
        .lastName("Пупкина")
        .age(21)
        .isMale(false)
        .isMarried(false));
    // when
    assertThat(vasilisaPupkina).isNotNull()
                               // then
                               .extracting(
                                   Person1::firstName,
                                   Person1::lastName,
                                   Person1::age,
                                   Person1::isMale,
                                   Person1::isMarried)
                               .contains("Василиса", "Пупкина", 21, false, false);
  }
}
