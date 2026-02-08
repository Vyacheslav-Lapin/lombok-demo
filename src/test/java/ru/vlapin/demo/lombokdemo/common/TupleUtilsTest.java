package ru.vlapin.demo.lombokdemo.common;

import static org.assertj.core.api.Assertions.*;

import io.vavr.Tuple1;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import lombok.experimental.ExtensionMethod;
import lombok.val;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link TupleUtils} class. This test class focuses on validating the functionality of the
 * {@link TupleUtils#toTuple1(Object)} method, ensuring that it properly converts an object into a {@link Tuple1}.
 */
@ExtensionMethod(value = TupleUtils.class, suppressBaseMethods = false)
class TupleUtilsTest {

  @Test
  void toTuple1_shouldConvertStringToTuple1() {
    // given
    val input = "test";

    // when
    assertThat(input.toTuple1())
        // then
        .isNotNull()
        .extracting(Tuple1::_1)
        .isEqualTo(input);
  }

  @Test
  void toTuple2_shouldConvertStringToTuple2() {
    // given
    val input = "test";

    // when
    assertThat(input.toTuple2())
        // then
        .isNotNull()
        .extracting(Tuple2::_1, Tuple2::_2)
        .containsExactly(input, input);
  }

  @Test
  void toTuple1_shouldConvertIntegerToTuple1() {
    // given
    val input = 42;

    // when
    assertThat(TupleUtils.toTuple1(input))
        // then
        .isNotNull()
        .extracting(Tuple1::_1)
        .isEqualTo(input);
  }

  @Test
  void toTuple2_shouldConvertIntegerToTuple2() {
    // given
    val input = 42;

    // when
    assertThat(TupleUtils.toTuple2(input))
        // then
        .isNotNull()
        .extracting(Tuple2::_1, Tuple2::_2)
        .containsExactly(input, input);
  }

  @Test
  void toTuple1_shouldConvertNullToTuple1() {
    // Arrange
    String input = null;

    // when
    assertThat(input.toTuple1())
        // then
        .isNotNull()
        .extracting(Tuple1::_1)
        .isEqualTo(input.toTuple1()._1);
  }

  @Test
  void toTuple2_shouldConvertNullToTuple2() {
    // Arrange
    String input = null;

    // when
    assertThat(input.toTuple2())
        // then
        .isNotNull()
        .extracting(Tuple2::_1, Tuple2::_2)
        .containsExactly(null, null);
  }

  @Test
  void toTuple3_shouldConvertStringToTuple3() {
    // given
    val input = "test";

    // when
    assertThat(input.toTuple3())
        // then
        .isNotNull()
        .extracting(Tuple3::_1, Tuple3::_2, Tuple3::_3)
        .containsExactly(input, input, input);
  }

  @Test
  void toTuple3_shouldConvertIntegerToTuple3() {
    // given
    val input = 42;

    // when
    assertThat(TupleUtils.toTuple3(input))
        // then
        .isNotNull()
        .extracting(Tuple3::_1, Tuple3::_2, Tuple3::_3)
        .containsExactly(input, input, input);
  }

  @Test
  void toTuple3_shouldConvertNullToTuple3() {
    // Arrange
    String input = null;

    // when
    assertThat(input.toTuple3())
        // then
        .isNotNull()
        .extracting(Tuple3::_1, Tuple3::_2, Tuple3::_3)
        .containsExactly(null, null, null);
  }
}
