package ru.vlapin.demo.lombokdemo.stable.builder.dsl;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;
import ru.vlapin.demo.lombokdemo.stable.builder.dsl.Person3.Address;

@DisplayNameGeneration(ReplaceCamelCase.class)
class Person2Test {

  @Test
  @DisplayName("Builder works correctly")
  void builderWorksCorrectlyTest() {
    // given
    val initial = Person3.of()//builder -> builder
                         .fio("Василий Петрович Пупкин")
                         .age(25)
                         .address(Address.of()//innerBuilder -> innerBuilder
                                         .city("Москва")
                                         .street("Lenina")
                                         .houseNumber(2)
                                         .apartment(111).build())
                         .contact("telephone1", "222-33-22")//.indexed("a")
                         .contact("telephone2", "333-22-33")//.indexed("isMarried")
                         .isMarried(true);

    // when
    assertThat(initial.build()).isNotNull()
                               // then
                               .extracting(
                                   Person3::fio,
                                   Person3::age,
                                   Person3::isMarried,
                                   it -> it.address().city(),
                                   it -> it.address().street(),
                                   it -> it.address().houseNumber(),
                                   it -> it.address().apartment(),
                                   it -> it.contacts().get("telephone1"),
                                   it -> it.contacts().get("telephone2"),
                                   it -> it.contacts().containsKey("telephone3"))
                               .contains(25, "Василий Петрович Пупкин", true, "Москва", "Lenina", 2, 111, "222-33-22", "333-22-33", false);

    val actual = initial
        .address(Address.of()//innerBuilder -> innerBuilder
                        .apartment(2)
                        .street("Ветеранов")
                        .city("Санкт-Петербург")
                        .houseNumber(3)
                        .build())
        .contact("telephone2", "777-77-77")
        .contact("telephone3", "888-88-88")
        .build();

    // when
    assertThat(actual)
        // then
        .isNotNull()
        .extracting(
            Person3::age,
            Person3::fio,
            Person3::isMarried,
            it -> it.address().city(),
            it -> it.address().street(),
            it -> it.address().houseNumber(),
            it -> it.address().apartment(),
            it -> it.contacts().get("telephone1"),
            it -> it.contacts().get("telephone2"),
            it -> it.contacts().get("telephone3"))
        .contains(25, "Василий Петрович Пупкин", true,
            "Санкт-Петербург", "Ветеранов", 3, 2, "222-33-22", "777-77-77", "888-88-88");
  }
}
