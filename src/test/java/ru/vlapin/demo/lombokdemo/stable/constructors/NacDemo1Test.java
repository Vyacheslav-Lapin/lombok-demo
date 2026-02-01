package ru.vlapin.demo.lombokdemo.stable.constructors;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * NacDemoTest.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class NacDemo1Test {

  @Test
  //@DisplayName("NoArgsConstructor annotation works correctly")
  void noArgsConstructorAnnotationWorksCorrectlyTest() {
    // given
    val nacDemo = new NacDemo1();

    // when
    assertThat(nacDemo).isNotNull()
        // then
        .extracting("x", "s", "z")
        .contains(0, null, false);
  }
}
