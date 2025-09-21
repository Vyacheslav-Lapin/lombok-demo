package ru.vlapin.demo.lombokdemo.stable.builder.singular.list;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.InstanceOfAssertFactories.*;

import java.util.Collections;
import java.util.List;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.PackagePrivate;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.ReflectionUtils;

@ExtensionMethod(value = ReflectionUtils.class, suppressBaseMethods = false)
class PersonTest {

  //todo 21.09.2025: Разобраться, почем этому полю нельзя поставить модификатор private?
  @PackagePrivate Class<Object> unmodifiableRandomAccessListClass = Collections.class.declaredClass("UnmodifiableRandomAccessList");

  @Test
  @DisplayName("Builder with collection works correctly")
  void builderWithCollectionWorksCorrectlyTest() {
    // given
    val vasyaPupkin = Person.builder()
                            .contacts(List.of("222-33-22",
                                "vpupkin@ya.ru",
                                "@vasya.pupkin"))
                            .build();

    // when
    assertThat(vasyaPupkin)
        // then
        .isNotNull()
        .extracting(Person::contacts, as(list(String.class)))
        .isNotEmpty()
        .isInstanceOf(List.class)
        .isExactlyInstanceOf(unmodifiableRandomAccessListClass)
        .containsExactly("222-33-22",
            "vpupkin@ya.ru",
            "@vasya.pupkin");
  }

  @Test
  @DisplayName("Singular annotation works correctly")
  void singularAnnotationWorksCorrectlyTest() {
    // given
    val vasyaPupkin = Person.builder()
                            .contact("222-33-22")
                            .contact("vpupkin@ya.ru")
                            .contact("@vasya.pupkin");

    // when
    assertThat(vasyaPupkin.build())
        // then
        .isNotNull()
        .extracting(Person::contacts, as(list(String.class)))
        .isNotEmpty()
        .isInstanceOf(List.class)
        .isExactlyInstanceOf(unmodifiableRandomAccessListClass)
        .containsExactly("222-33-22",
            "vpupkin@ya.ru",
            "@vasya.pupkin");

    val emptyListClass = Collections.class.declaredClass("EmptyList");

    // when
    assertThat(vasyaPupkin.clearContacts()
                          .build())
        // then
        .isNotNull()
        .extracting(Person::contacts, as(list(String.class)))
        .isInstanceOf(List.class)
        .isExactlyInstanceOf(emptyListClass)
        .isEmpty();
  }
}
