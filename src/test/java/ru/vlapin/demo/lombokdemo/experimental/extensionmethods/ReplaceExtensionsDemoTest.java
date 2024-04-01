package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.experimental.extensionmethods.ReplaceExtensionsDemo.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
class ReplaceExtensionsDemoTest {

  @Test
  @DisplayName("ExtensionMethod annotation works correctly")
  void extensionMethodAnnotationWorksCorrectlyTest() {
    // given
    val demo = demo(1, 2, 3);
    // when
    assertThat(demo).isNotNull()
                    // then
                    .isNotEmpty()
                    .isEqualTo("[1, 2, 3]");
  }
}
