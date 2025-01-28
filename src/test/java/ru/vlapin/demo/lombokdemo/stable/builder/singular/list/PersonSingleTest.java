package ru.vlapin.demo.lombokdemo.stable.builder.singular.list;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.InstanceOfAssertFactories.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PersonSingleTest {

  @Test
  @DisplayName("Singular with param works correctly")
  void singularWithParamWorksCorrectlyTest() {
    // given
    val vPupkin = PersonSingle.builder()
                              .contactEntry("222-33-22")
                              .contactEntry("vpupkin@ya.ru")
                              .contactEntry("@vasya.pupkin");

    // when
    assertThat(vPupkin.build())
        // then
        .isNotNull()
        .extracting(PersonSingle::contact,
            as(list(String.class)))
        .isNotEmpty()
        .containsExactly("222-33-22",
            "vpupkin@ya.ru",
            "@vasya.pupkin");
    // when
    assertThat(vPupkin.clearContact()
                      .build())
        // then
        .isNotNull()
        .extracting(PersonSingle::contact,
            as(list(String.class)))
        .isEmpty();
  }
}
