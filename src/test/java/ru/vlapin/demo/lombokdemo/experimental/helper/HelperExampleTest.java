package ru.vlapin.demo.lombokdemo.experimental.helper;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
class HelperExampleTest {

  @Test
  @DisplayName("@Helper lombok annotation works correctly")
  void helperLombokAnnotationWorksCorrectlyTest() {
    assertThat(new HelperExample().someMethod(5))
        .isEqualTo(20);
  }
}
