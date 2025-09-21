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
class PersonCollectionTest {

  //todo 21.09.2025: Разобраться, почему в этим двум полям нельзя поставить модификатор private?
  @PackagePrivate Class<Object> unmodifiableRandomAccessListClass = Collections.class.declaredClass("UnmodifiableRandomAccessList");
  @PackagePrivate Class<Object> emptyListClass = Collections.class.declaredClass("EmptyList");

  @Test
  @DisplayName("@Singular annotation works identically before list and collection")
  void singularAnnotationWorksIdenticallyBeforeListAndCollectionTest() {
    // given
    val vasyaPupkin = PersonCollection.builder()
                            .contact("222-33-22")
                            .contact("vpupkin@ya.ru")
                            .contact("@vasya.pupkin");

    // when
    assertThat(vasyaPupkin.build())
        .isNotNull()
        // then
        .extracting(PersonCollection::contacts,
            as(list(String.class)))
        .isInstanceOf(List.class)
        .isExactlyInstanceOf(unmodifiableRandomAccessListClass)
        .isNotEmpty()
        .containsExactly("222-33-22",
            "vpupkin@ya.ru",
            "@vasya.pupkin");

    // when
    assertThat(vasyaPupkin.clearContacts()
                          .build())
        // then
        .isNotNull()
        .extracting(PersonCollection::contacts,
            as(list(String.class)))
        .isInstanceOf(List.class)
        .isExactlyInstanceOf(emptyListClass)
        .isEmpty();
  }
}
