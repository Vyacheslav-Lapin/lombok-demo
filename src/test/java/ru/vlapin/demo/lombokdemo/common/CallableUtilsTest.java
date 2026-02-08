package ru.vlapin.demo.lombokdemo.common;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.vavr.CheckedFunction0;
import java.util.concurrent.Callable;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * Unit tests for {@link CallableUtils}.
 * <p>
 * This test class verifies the behavior of the toChecked method, ensuring it correctly wraps a {@link Callable} into a
 * {@link CheckedFunction0}, allowing exceptions to be handled functionally.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class CallableUtilsTest {

  @Test
  @SneakyThrows
  @DisplayName("toChecked converts callable that successfully returns a value")
  void toCheckedConvertsCallableThatSuccessfullyReturnsAValueTest() {
    // given
    val callable = mock(Callable.class);
    when(callable.call()).thenReturn("Test Value");

    // when
    val checkedFunction = CallableUtils.toChecked(callable);

    // then
    assertThat(checkedFunction).isNotNull();
    assertThat(checkedFunction.apply()).isEqualTo("Test Value");
    verify(callable, times(1)).call();
  }

  @Test
  @SneakyThrows
  @DisplayName("toChecked converts callable that throws an exception")
  void toCheckedConvertsCallableThatThrowsAnExceptionTest() {
    // given
    val callable = mock(Callable.class);
    when(callable.call()).thenThrow(new IllegalStateException("Test Exception"));

    // when
    val checkedFunction = CallableUtils.toChecked(callable);

    // then
    assertThat(checkedFunction).isNotNull();
    assertThatThrownBy(checkedFunction::apply)
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("Test Exception");
    verify(callable, times(1)).call();
  }

  @Test
  @SneakyThrows
  @DisplayName("toChecked handles callable returning null")
  void toCheckedHandlesCallableReturningNullTest() {
    // given
    val callable = mock(Callable.class);
    when(callable.call()).thenReturn(null);

    // when
    val checkedFunction = CallableUtils.toChecked(callable);

    // then
    assertThat(checkedFunction).isNotNull();
    assertThat(checkedFunction.apply()).isNull();
    verify(callable, times(1)).call();
  }

  @Test
  @SneakyThrows
  @DisplayName("toChecked handles callable with non-string return type")
  void toCheckedHandlesCallableWithNonStringReturnTypeTest() {
    // given
    val callable = mock(Callable.class);
    when(callable.call()).thenReturn(42);

    // when
    val checkedFunction = CallableUtils.toChecked(callable);

    // then
    assertThat(checkedFunction).isNotNull();
    assertThat(checkedFunction.apply()).isEqualTo(42);
    verify(callable, times(1)).call();
  }

  @Test
  @SneakyThrows
  @DisplayName("toChecked works with a real callable")
  void toCheckedWorksWithARealCallableTest() {
    // given
    Callable<String> callable = () -> "Real Callable Value";

    // when
    val checkedFunction = CallableUtils.toChecked(callable);

    // then
    assertThat(checkedFunction).isNotNull();
    assertThat(checkedFunction.apply()).isEqualTo("Real Callable Value");
  }
}
