package ru.vlapin.demo.lombokdemo.stable.builder.dsl;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.stable.builder.dsl.Person3.Address;

class Person2Test {

  @Test
  @DisplayName("Builder works correctly")
  void builderWorksCorrectlyTest() {
    // given
    val initial = Person3.of()//builder -> builder
                         .fio("Василий Петрович Пупкин")
                         .age(25)
                         .address(Address.of()//innerBuilder -> innerBuilder
                                         .city("Moscow")
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
                               .contains(25, "Василий Петрович Пупкин", true, "Moscow", "Lenina", 2, 111, "222-33-22", "333-22-33", false);

    val actual = initial
        .address(Address.of()//innerBuilder -> innerBuilder
                        .apartment(2)
                        .street("Ветеранов")
                        .city("Moscow")
                        .houseNumber(2)
                        .build())
        .contact("telephone2", "777-77-77")//.indexed("isMarried")
        .build();

//    // when
//    assertThat(actual.build())
//        // then
//        .isNotNull()
//        .extracting(
//            Person0::age,
//            Person0::fio,
//            Person0::isMarried,
//            it -> it.inner().y(),
//            it -> it.inner().z(),
//            it -> it.inner().props().get("prop1"),
//            it -> it.inner().props().get("prop2"),
//            it -> it.inner().props().containsKey("prop3"))
//        .contains(1, "lorem", true, 2, "dolor", "a", "isMarried", false);
  }
}
