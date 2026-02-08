package ru.vlapin.demo.lombokdemo.common;

import io.vavr.CheckedFunction0;
import java.util.concurrent.Callable;
import lombok.experimental.UtilityClass;

/**
 * A utility class for working with {@link Callable} objects.
 * Provides methods for type casting and functional transformation of callables.
 */
@UtilityClass
public class CallableUtils {

  /**
   * Casts a {@link Callable} with a broader type parameter to a {@link Callable} with a narrower type parameter.
   *
   * @param <T> The type parameter of the resulting {@link Callable}.
   * @param callable The input {@link Callable} whose result type can be assigned to {@code T}.
   * @return The input {@link Callable} cast to {@link Callable} with the type parameter {@code T}.
   */
  @SuppressWarnings({"unchecked", "unused", "java:S100"})
  public <T> Callable<T> Callable(Callable<? extends T> callable) {
    return (Callable<T>) callable;
  }

  /**
   * Casts a {@link Callable} with a broader type parameter to a {@link Callable} with a narrower type parameter.
   *
   * @param <T> The type parameter of the resulting {@link Callable}.
   * @param callable The input {@link Callable} whose result type can be assigned to {@code T}.
   * @return The input {@link Callable} cast to {@link Callable} with the type parameter {@code T}.
   */
  @SuppressWarnings("unchecked")
  public <T> Callable<T> of(Callable<? extends T> callable) {
    return (Callable<T>) callable;
  }

  /**
   * Converts a {@link Callable} object to a {@link CheckedFunction0}.
   * This enables working with the {@link Callable} in a functional programming style
   * while preserving its ability to throw checked exceptions.
   *
   * @param <T> The type of the result produced by the {@link Callable}.
   * @param callable The {@link Callable} to be converted to a {@link CheckedFunction0}.
   * @return A {@link CheckedFunction0} that delegates to the input {@link Callable}'s {@code call()} method.
   */
  public <T> CheckedFunction0<T> toChecked(Callable<? extends T> callable) {
    return callable::call;
  }
}
