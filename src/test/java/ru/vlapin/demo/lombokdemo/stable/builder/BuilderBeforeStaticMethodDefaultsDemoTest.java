package ru.vlapin.demo.lombokdemo.stable.builder;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.stable.builder.BuilderBeforeStaticMethodDefaultsDemo.*;

import java.time.LocalDate;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
class BuilderBeforeStaticMethodDefaultsDemoTest {

  @Test
  @SneakyThrows
  @DisplayName("Defaults works correctly")
  void defaultsWorksCorrectlyTest() {
    // given
    val beforeNowCaller = beforeNowCaller();

    // when
    assertThat(beforeNowCaller.call())
        // then
        .isNotNull()
        .isEqualTo(LocalDate.now()
                            .minusDays(1)
                            .minusMonths(1));

    // when
    assertThat(beforeNowCaller.withMonths(2)
                              .call())
        // then
        .isNotNull()
        .isEqualTo(LocalDate.now()
                            .minusDays(1)
                            .minusMonths(2));
  }
}
