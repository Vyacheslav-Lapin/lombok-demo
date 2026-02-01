package ru.vlapin.demo.lombokdemo.stable.constructors;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.stable.constructors.WithoutNewDemo.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * WithoutNewDemoTest.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class WithoutNewDemoTest {

  @Test
  @DisplayName("Object creates correctly without new keyword")
  void objectCreatesCorrectlyWithoutNewKeywordTest() {
    // given
    val obj = WithoutNewDemo(1, "lor", true);

    // when
    assertThat(obj).isNotNull()
        // then
        .extracting(
            WithoutNewDemo::getX,
            WithoutNewDemo::getS,
            WithoutNewDemo::isB)
        .contains(1, "lor", true);
  }
}
