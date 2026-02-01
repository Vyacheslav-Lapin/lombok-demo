package ru.vlapin.demo.lombokdemo.experimental.utilityclass;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.experimental.utilityclass.UtilityClassExample.*;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
class UtilityClassExampleTests {

  @Test
  @SneakyThrows
  @DisplayName("UtilityClass lombok annotation works correctly")
  void utilityClassLombokAnnotationWorksCorrectlyTest() {
    assertThat(UtilityClassExample.class).isFinal();
    assertThat(addSomething(1)).isEqualTo(6);
  }
}
