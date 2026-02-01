package ru.vlapin.demo.lombokdemo.stable.with;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
class WithBeforeClassDemoTests {

  @Test
  @DisplayName("With annotation for class works correctly")
  void withAnnotation4ClassWorksCorrectlyTest() {
    //given
    val foo = new WithBeforeClassDemo(
        1,
        "lorem",
        true);

    //when
    assertThat(foo.withS("ipsum")
                  .withB(false))
        //then
        .isNotNull()
        .isNotSameAs(foo)
        .extracting("x", "s", "b")
        .contains(1, "ipsum", false);
  }
}
