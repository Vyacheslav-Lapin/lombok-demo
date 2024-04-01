package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.experimental.extensionmethods.AddExtensionsDemo.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
class AddExtensionsDemoTest {

  @Test
  @DisplayName("ExtensionMethod annotation works correctly for sorting")
  void extensionMethodAnnotationWorksCorrectlyForSortingTest() {
    // given
    val sortedArray = getSortedArray(5, 3, 8, 2);

    // when
    assertThat(sortedArray).isNotNull()
        // then
        .containsExactly(2, 3, 5, 8);
  }
}
