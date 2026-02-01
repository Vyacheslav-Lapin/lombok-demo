package ru.vlapin.demo.lombokdemo.stable.data;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * DataDemoTest.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class DataDemoTest {

  @Test
  @DisplayName("Data annotation works correctly")
  void dataAnnotationWorksCorrectlyTest() {
    // given
    val dataDemo = new DataDemo("lor")
        .setX(1).setB(true);

    val dataDemo1 = new DataDemo("lor")
        .setX(1).setB(true);

    val dataDemo2 = new DataDemo("lor")
        .setX(1).setB(false);

    // when
    assertThat(dataDemo)
        // then
        .isEqualTo(dataDemo1)
        .isNotEqualTo(dataDemo2)
        .hasToString("DataDemo(x=1, s=lor, b=true)")
        .extracting(DataDemo::getS)
        .isEqualTo("lor");
  }
}
