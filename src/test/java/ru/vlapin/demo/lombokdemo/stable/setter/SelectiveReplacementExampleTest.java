package ru.vlapin.demo.lombokdemo.stable.setter;

import static org.assertj.core.api.Assertions.*;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * SelectiveReplacementExampleTest.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class SelectiveReplacementExampleTest {

  @Test
  @SneakyThrows
  //@DisplayName("Principle of unobtrusiveness works correctly")
  void principleOfUnobtrusivenessWorksCorrectlyTest() {
    // given
    val sre = new SelectiveReplacementExample();

    sre.setX("1");
    sre.setY(2);
    sre.setZ(3);

    // when
    assertThat(sre).isNotNull()
        // then
        .extracting(
            SelectiveReplacementExample::getX,
            SelectiveReplacementExample::getY,
            SelectiveReplacementExample::getZ)
        .contains(1, 2, 3);

    // when
    assertThatThrownBy(() -> SelectiveReplacementExample.class
        .getDeclaredMethod("setX", int.class))
        // then
        .isInstanceOf(NoSuchMethodException.class)
        .hasMessage("ru.vlapin.demo.lombokdemo.stable.setter.SelectiveReplacementExample.setX(int)");
  }
}
