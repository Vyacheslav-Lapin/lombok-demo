package ru.vlapin.demo.lombokdemo.stable.builder.singular.list;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.InstanceOfAssertFactories.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PersonWithChildrenTest {

  @Test
  @DisplayName("Singular annotation works correctly")
  void singularAnnotationWorksCorrectlyTest() {
    // given
    val vasyaPupkin = PersonWithChildren.builder()
                                        .child("222-33-22")
                                        .child("vpupkin@ya.ru")
                                        .child("@vasya.pupkin");

    // when
    assertThat(vasyaPupkin.build())
        .isNotNull()
        // then
        .extracting(PersonWithChildren::children,
            as(list(String.class)))
        .isNotEmpty()
        .containsExactly("222-33-22",
            "vpupkin@ya.ru",
            "@vasya.pupkin");
    // when
    assertThat(vasyaPupkin.clearChildren()
                          .build())
        // then
        .isNotNull()
        .extracting(PersonWithChildren::children,
            as(list(String.class)))
        .isEmpty();
  }
}
