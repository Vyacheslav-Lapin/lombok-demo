package ru.vlapin.demo.lombokdemo.stable.builder;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
class ToBuilderDemoTests {

  @Test
  @DisplayName("@Builder with toBuilder param works correctly")
  void builderWithToBuilderParamWorksCorrectlyTest() {
    // Как использовать один объект...
    val demo = ToBuilderDemo.builder()
                   .s("lorem")
                   .x(100_500)
                   .b(true).build();
    //...в качесте исходных значений для другого?
    assertThat(demo.toBuilder()
                   .b(false)
                   .s("ipsum").build())
        .extracting("x", "s", "b")
        .contains(100_500, "ipsum", false);
  }
}
