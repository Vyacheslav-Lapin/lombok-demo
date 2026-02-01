package ru.vlapin.demo.lombokdemo.stable.constructors;

import static org.assertj.core.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * ScopeDemoTest.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class ScopeDemoTest {

  @Test
  @DisplayName("AllArgsConstructor scope changes correctly")
  void allArgsConstructorScopeChangesCorrectlyTest() {

    // given
    val constructor =
        ScopeDemo.class
            .getDeclaredConstructors()[0];

    // when
    assertThat(constructor)
        // then
        .extracting(Constructor::getModifiers)
        .matches(Modifier::isPrivate);
  }
}
