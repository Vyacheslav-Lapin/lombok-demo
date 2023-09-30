package ru.vlapin.demo.lombokdemo.experimental.getter;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils;

import static org.assertj.core.api.Assertions.*;

@DisplayNameGeneration(TestUtils.ReplaceCamelCase.class)
class PrefixExample1Test {

  @Test
  @SneakyThrows
//  @DisplayName("Prefix param works correctly")
  void prefixParamWorksCorrectlyTest() {
    // given
    val lorem = new PrefixExample1()
        .setName("Lorem");

    // when
    assertThat(lorem).isNotNull()
        // then
        .extracting(PrefixExample1::getName)
        .isEqualTo("Lorem");
  }
}
