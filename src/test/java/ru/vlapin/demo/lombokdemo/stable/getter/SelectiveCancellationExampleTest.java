package ru.vlapin.demo.lombokdemo.stable.getter;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * SelectiveCancellationExampleTest.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class SelectiveCancellationExampleTest {

  @Test
  @DisplayName("selective cancellation works correctly")
  @SuppressWarnings({"SpellCheckingInspection", "JavaReflectionMemberAccess"})
  void selectiveCancellationWorksCorrectlyTest() {
    // given
    val sce = new SelectiveCancellationExample();

    // when
    sce.setX(1);
    sce.setY(2);
//    sce.setZ(3);

    // then
    assertThat(sce).isNotNull()
        .extracting(
            SelectiveCancellationExample::getX,
            SelectiveCancellationExample::getY)
        .contains(1, 2);

    assertThatThrownBy(() ->
        SelectiveCancellationExample.class
            .getDeclaredMethod("getZ"))
        .isInstanceOf(NoSuchMethodException.class)
        .hasMessage("ru.vlapin.demo.lombokdemo.stable.getter.SelectiveCancellationExample.getZ()");

    assertThatThrownBy(() ->
        SelectiveCancellationExample.class
            .getDeclaredMethod("setZ", int.class))
        .isInstanceOf(NoSuchMethodException.class)
        .hasMessage("ru.vlapin.demo.lombokdemo.stable.getter.SelectiveCancellationExample.setZ(int)");
  }
}
