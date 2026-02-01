package ru.vlapin.demo.lombokdemo.stable.constructors;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * NacDemo3Test.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class NacDemo3Test {

  @Test
  @DisplayName("@NoArgsConstructor annotation works correctly")
  void noArgsConstructorAnnotationWorksCorrectlyTest() {
    // given
    val nacDemo = new NacDemo3();

    // when
    assertThat(nacDemo)
        // then
        .extracting("x", "s", "z")
        .contains(0, null, false);
  }
}
