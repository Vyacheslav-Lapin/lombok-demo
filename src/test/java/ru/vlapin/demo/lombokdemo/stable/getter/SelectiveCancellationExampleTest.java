package ru.vlapin.demo.lombokdemo.stable.getter;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * SelectiveCancellationExampleTest.
 */
class SelectiveCancellationExampleTest {

  @Test
  @DisplayName("selective canncellation works correctly")
  void selectiveCanncellationWorksCorrectlyTest() {
    // given
    val sce = new SelectiveCancellationExample();

    // when
    sce.setX(1);
    sce.setY(2);
//    sce.setZ(3);

    // then
    Assertions.assertThat(sce).isNotNull()
        .extracting(
            SelectiveCancellationExample::getX,
            SelectiveCancellationExample::getY)
        .contains(1, 2);

    Assertions.assertThatThrownBy(() ->
        SelectiveCancellationExample.class
            .getDeclaredMethod("getZ"))
        .isInstanceOf(NoSuchMethodException.class)
        .hasMessage("ru.vlapin.demo.lombokdemo.stable.getter.SelectiveCancellationExample.getZ()");

    Assertions.assertThatThrownBy(() ->
        SelectiveCancellationExample.class
            .getDeclaredMethod("setZ", int.class))
        .isInstanceOf(NoSuchMethodException.class)
        .hasMessage("ru.vlapin.demo.lombokdemo.stable.getter.SelectiveCancellationExample.setZ(int)");
  }
}
