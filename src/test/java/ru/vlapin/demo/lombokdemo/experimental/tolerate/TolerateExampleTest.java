package ru.vlapin.demo.lombokdemo.experimental.tolerate;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * TolerateExampleTest.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class TolerateExampleTest {

  @Test
//  @DisplayName("Tolerate annotation works correctly")
  void tolerateAnnotationWorksCorrectlyTest() {
    // given
    val se = new TolerateExample();

    // Как добиться, чтобы был и стандартный,..
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
