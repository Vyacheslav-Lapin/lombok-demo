package ru.vlapin.demo.lombokdemo.stable.builder;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ToBuilderDemoTests {

  @Test
  @SneakyThrows
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
