package ru.vlapin.demo.lombokdemo.stable.builder;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.stable.builder.BuilderBeforeStaticMethodPrefixedDemo.BeforeNowCaller;

class BuilderBeforeStaticMethodPrefixedDemoTest {

  @Test
  @DisplayName("@Builder before static-method works correctly")
  void builderBeforeStaticMethodWorksCorrectlyTest() {
    BeforeNowCaller beforeNowCaller =
        BuilderBeforeStaticMethodPrefixedDemo
            .beforeNowCaller()
            .withMonths(3)
            .withDays(1);

    assertThat(beforeNowCaller.call())
        .isNotNull()
        .isEqualTo(LocalDate.now()
                            .minusDays(1)
                            .minusMonths(3));
  }
}
