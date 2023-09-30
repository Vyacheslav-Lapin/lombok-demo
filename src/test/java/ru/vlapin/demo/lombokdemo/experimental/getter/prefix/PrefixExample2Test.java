package ru.vlapin.demo.lombokdemo.experimental.getter.prefix;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

import static org.assertj.core.api.Assertions.*;

@DisplayNameGeneration(ReplaceCamelCase.class)
class PrefixExample2Test {

  @Test
  @SneakyThrows
//  @DisplayName("Prefix param works correctly")
  void prefixParamWorksCorrectlyTest() {
    // given
    val lorem = new PrefixExample2()
        .setName("Lorem");

    // when
    assertThat(lorem).isNotNull()
        // then
        .extracting(PrefixExample2::getName)
        .isEqualTo("Lorem");
  }
}
