package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.experimental.extensionmethods.AddCustomExtensionsDemo.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
class AddCustomExtensionsDemoTests {

  @Test
  @SuppressWarnings("java:S5845")
  @DisplayName("UseExtensionMethods method works correctly")
  void titledMethodWorksCorrectlyTest() {
    // given
    val demo = AddCustomExtensionsDemo("aNOIMOUS");

    //when
    assertThat(demo.titled("vasya"))
        // then
        .isNotNull()
        .isEqualTo("Vasya");

    // when
    assertThat(demo.titled(null))
        // then
        .isNotNull()
        .isEqualTo("Anoimous");
  }
}
