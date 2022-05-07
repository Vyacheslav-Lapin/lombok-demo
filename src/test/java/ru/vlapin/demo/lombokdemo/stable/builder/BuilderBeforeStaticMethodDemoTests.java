package ru.vlapin.demo.lombokdemo.stable.builder;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BuilderBeforeStaticMethodDemoTests {

  @Test
  @DisplayName("@Builder before static-method works correctly")
  void builderBeforeStaticMethodWorksCorrectlyTest() {
    assertThat(BuilderBeforeStaticMethodDemo.builder()
                   .months(3)
                   .days(1).build()).isNotNull()
        .isEqualTo(LocalDate.now()
                       .minusDays(1)
                       .minusMonths(3));
  }
}
