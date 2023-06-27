package ru.vlapin.demo.lombokdemo.experimental.tolerate;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TolerateExampleTest.
 */
class TolerateExampleTest {

  @Test
  @DisplayName("Tolerate annotation works correctly")
  void tolerateAnnotationWorksCorrectlyTest() {
    // given
    val se = new TolerateExample();

    // Как добиться, что бы был и стандартный,..
    se.setX(5);
    assertThat(se).isNotNull()
        .extracting(
            TolerateExample::getX)
        .isEqualTo(5);

    // ...и - специальный setter?
    se.setX("15");
    assertThat(se).isNotNull()
        .extracting(
            TolerateExample::getX)
        .isEqualTo(15);

  }
}
