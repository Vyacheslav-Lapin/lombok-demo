package ru.vlapin.demo.lombokdemo.dto;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PersonTest {

  @Test
  //@lombok.SneakyThrows
  @DisplayName("Builder works correctly")
  void builderWorksCorrectlyTest() {
    // given
    val vasya = Person.builder()
                     .firstName("Вася")
                     .age(45)
                     .contact("222-33-22")
                     .contact("Tdfgsdf@kjshdf.ru")
                     .build();

    // when
    assertThat(vasya).isNotNull()
        // then
        .hasFieldOrPropertyWithValue("firstName", "Вася")
        .hasFieldOrPropertyWithValue("lastName", "Мирошниченко")
        .hasFieldOrPropertyWithValue("age", 45)
        .hasFieldOrPropertyWithValue("contacts", List.of("222-33-22", "Tdfgsdf@kjshdf.ru"));
  }
}
