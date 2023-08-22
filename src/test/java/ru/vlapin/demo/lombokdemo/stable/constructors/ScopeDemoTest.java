package ru.vlapin.demo.lombokdemo.stable.constructors;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.*;

/**
 * ScopeDemoTest.
 */
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
