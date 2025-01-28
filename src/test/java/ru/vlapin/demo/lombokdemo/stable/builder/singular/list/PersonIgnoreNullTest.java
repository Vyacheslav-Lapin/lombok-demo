package ru.vlapin.demo.lombokdemo.stable.builder.singular.list;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.InstanceOfAssertFactories.*;

import java.util.List;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PersonIgnoreNullTest {

  @Test
  @DisplayName("Singular annotation throws NPE exception when get null as plural-method parameter")
  void singularAnnotationThrowsNPEExceptionWhenGetNullAsPluralMethodParameterTest() {
    // given
    val personBuilder = Person.builder();
    // when
    assertThatThrownBy(() -> personBuilder.contacts(null))
        // then
        .isExactlyInstanceOf(NullPointerException.class)
        .hasMessage("contacts is marked non-null but is null");
  }

  @Test
  @DisplayName("Singular annotation with ignoreNullCollections param works correctly")
  void singularAnnotationWithIgnoreNullCollectionsParamWorksCorrectlyTest() {
    // given
    val vasyaPupkin = PersonIgnoreNull.builder()
                                      .contacts(null);

    // when
    assertThat(vasyaPupkin.build())
        // then
        .isNotNull()
        .extracting(PersonIgnoreNull::contacts, as(list(String.class)))
        .isNotNull()
        .isEmpty();

    // when
    assertThat(vasyaPupkin.contacts(List.of("222-33-22")).build())
        // then
        .isNotNull()
        .extracting(PersonIgnoreNull::contacts, as(list(String.class)))
        .isNotNull()
        .isNotEmpty()
        .hasSize(1)
        .containsExactly("222-33-22");
  }
}
