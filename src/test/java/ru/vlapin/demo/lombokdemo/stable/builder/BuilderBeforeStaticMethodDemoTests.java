package ru.vlapin.demo.lombokdemo.stable.builder;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BuilderBeforeStaticMethodDemoTests {

  @Test
  @DisplayName("@Builder before static-method works correctly")
  void builderBeforeStaticMethodWorksCorrectlyTest() {
    val localDate =
        BuilderBeforeStaticMethodDemo
            .beforeNowBuilder()
            .months(3)
            .days(1)
            .call();

    assertThat(localDate)
        .isNotNull()
        .isEqualTo(LocalDate.now()
                       .minusDays(1)
                       .minusMonths(3));
  }
}
