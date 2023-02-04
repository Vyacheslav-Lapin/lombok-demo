package ru.vlapin.demo.lombokdemo.stable.with;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class WithSimpleDemoTests {

  @Test
  @SneakyThrows
  @DisplayName("With annotation works correctly")
  void withAnnotationWorksCorrectlyTest() {
    //Тест (acceptance criteria):
    val foo = new WithSimpleDemo(1, "lorem", true);

    assertThat(foo).isNotNull()
        .extracting("x", "s", "b")
        .contains(1, "lorem", true);

    assertThat(foo.withX(2)).isNotNull()
        .extracting("x", "s", "b")
        .contains(2, "lorem", true);
  }
}
