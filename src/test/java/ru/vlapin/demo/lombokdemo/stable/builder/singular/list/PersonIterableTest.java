package ru.vlapin.demo.lombokdemo.stable.builder.singular.list;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.InstanceOfAssertFactories.*;

import java.util.Collections;
import java.util.List;
import lombok.experimental.ExtensionMethod;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.ReflectionUtils;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@SuppressWarnings("java:S1872")
@ExtensionMethod(value = ReflectionUtils.class, suppressBaseMethods =false)
@DisplayNameGeneration(ReplaceCamelCase.class)
class PersonIterableTest {

  @Test
  @DisplayName("@Singular annotations works correctly with Iterable")
  void singularAnnotationsWorksCorrectlyWithIterableTest() {
    // given
    val vasyaPupkin = PersonIterable.builder()
                                      .contact("222-33-22")
                                      .contact("vpupkin@ya.ru")
                                      .contact("@vasya.pupkin");

    val unmodifiableRandomAccessListClass = Collections.class.declaredClass("UnmodifiableRandomAccessList");

    // when
    assertThat(vasyaPupkin.build())
        .isNotNull()
        // then
        .extracting(PersonIterable::contacts,
            as(list(String.class)))
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
        .extracting(PersonIterable::contacts,
            as(list(String.class)))
        .isInstanceOf(List.class)
        .isExactlyInstanceOf(emptyListClass)
        .isEmpty();
  }
}
