package ru.vlapin.demo.lombokdemo.stable.builder;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.stable.builder.BuilderBeforeStaticMethodRequiredDemo.*;

import java.time.LocalDate;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BuilderBeforeStaticMethodRequiredDemoTest {

  @Test
  @DisplayName("Required params works correctly")
  void requiredParamsWorksCorrectlyTest() {
    // given
    val beforeNowCaller = beforeNowCaller(2);
    // when
    assertThat(beforeNowCaller.call())
        // then
        .isNotNull()
        .isEqualTo(LocalDate.now()
                            .minusDays(2)
                            .minusMonths(1));
  }
}
