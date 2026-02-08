package ru.vlapin.demo.lombokdemo.common;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedFunction1;
import io.vavr.CheckedFunction2;
import io.vavr.CheckedPredicate;
import java.util.Objects;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Utility class providing various scope functions for handling objects in a concise and functional approach.
 * The methods in this class can be used to reduce boilerplate and improve readability
 * when working with objects and their transformations or conditional evaluations.
 * <p>
 * This class leverages functional-style programming constructs with methods such as consumers,
 * functions, and predicates, and includes null-safety utilities for processing objects conditionally
 * based on their nullability.
 * <p>
 * The key methods provided are:
 * - {@code with}: Executes a consumer block on the given object.
 * - {@code peakWith}: Performs an additional operation on the object without altering it and then returns the object.
 * - {@code mapWith}: Applies a mapping function to transform the object.
 * - {@code doWithIfNotNull}: Executes a mapping function only if an additional operand is non-null; otherwise, returns the original object.
 * - {@code mapWithIfOrElse}: Applies one of two mapping functions based on a condition.
 * - {@code orIfNull}: Provides a default value or computes an alternative value if the object is null.
 * - {@code mapOrIfNull}: Maps an object if not null or provides an alternative value if null.
 */
@UtilityClass
@ExtensionMethod(value = Objects.class,
                 suppressBaseMethods = false)
public class ScopeFunctions {

  /**
   * Executes the provided {@code CheckedConsumer} on the given context object.
   *
   * @param <T> The type of the context object.
   * @param $this The context object on which the {@code checkedConsumer} will be executed.
   * @param checkedConsumer A consumer that performs operations on the given context object
   *                        and may throw checked exceptions.
   */
  public <T> void with(T $this,
                       CheckedConsumer<? super T> checkedConsumer) {
    checkedConsumer.unchecked().accept($this);
  }

  /**
   * Executes the provided {@code CheckedConsumer} on the given context object and returns the
   * object itself, allowing for chaining operations on the context.
   *
   * @param <T> The type of the context object.
   * @param $this The context object on which the {@code checkedConsumer} will be executed.
   * @param checkedConsumer A consumer that performs operations on the given context object
   *                        and may throw checked exceptions.
   * @return The given context object after the {@code checkedConsumer} has been executed.
   */
  public <T> T peakWith(T $this,
                        CheckedConsumer<? super T> checkedConsumer) {
    with($this, checkedConsumer);
    return $this;
  }

  /**
   * Applies the provided {@code CheckedFunction1} to the given context object and returns the result.
   *
   * @param <T> The type of the context object.
   * @param <R> The type of the result after applying the {@code mapper}.
   * @param $this The context object to be transformed.
   * @param mapper A function that transforms the context object into a result and may throw checked exceptions.
   * @return The result of applying the {@code mapper} to the given context object.
   */
  public <T, R> R mapWith(T $this,
                          CheckedFunction1<? super T, ? extends R> mapper) {
    return mapper.unchecked().apply($this);
  }

  /**
   * Applies the provided {@code mapper} function to the given context object and operand if the operand is not null.
   * If the operand is null, the original context object is returned unchanged.
   *
   * @param <T> The type of the context object.
   * @param <U> The type of the operand.
   * @param $this The context object to be processed by the {@code mapper}.
   * @param operand The operand which, if not null, will be used in the {@code mapper} function.
   * @param mapper A function that takes the context object and operand as inputs and returns a transformed context object.
   *               The function may throw checked exceptions.
   * @return The result of applying the {@code mapper} to the context object and operand, or
   *         the original context object if the operand is null.
   */
  public <T, U> T doWithIfNotNull(T $this,
                                  @Nullable U operand,
                                  CheckedFunction2<? super T, ? super U, ? extends T> mapper) {
    return operand.isNull() ? $this : mapper.unchecked().apply($this, operand);
  }

  /**
   * Transforms the context object based on the evaluation of a condition. The method applies one of
   * two provided mapping functions depending on whether the condition evaluates to true or false.
   *
   * @param <T> The type of the context object.
   * @param <R> The type of the result after applying the mapping function.
   * @param $this The context object to be transformed.
   * @param condition A predicate that evaluates the context object to determine which mapping function to apply.
   *                  May throw checked exceptions.
   * @param mapperIfTrue The mapping function to apply if the condition evaluates to true.
   *                     May throw checked exceptions.
   * @param mapperIfFalse The mapping function to apply if the condition evaluates to false.
   *                      May throw checked exceptions.
   * @return The result of applying either {@code mapperIfTrue} or {@code mapperIfFalse} to the context object,
   *         based on the evaluation of {@code condition}.
   */
  public <T, R> R mapWithIfOrElse(T $this,
                                  CheckedPredicate<? super T> condition,
                                  CheckedFunction1<? super T, ? extends R> mapperIfTrue,
                                  CheckedFunction1<? super T, ? extends R> mapperIfFalse) {
    return mapWith($this,
        condition.unchecked().test($this) ?
            mapperIfTrue
            : mapperIfFalse);
  }

  /**
   * Returns the provided alternative if the input object is null, otherwise returns the input object itself.
   *
   * @param <T> The type of the input object and the alternative.
   * @param $this The input object to be checked for nullity.
   * @param alternative The object to return if the input object is null.
   * @return The input object if it is not null; otherwise, the alternative.
   */
  public <T> T orIfNull(@Nullable T $this,
                        T alternative) {
    return $this.isNull() ? alternative : $this;
  }

  /**
   * Returns the input object if it is not null; otherwise, computes and returns an alternative
   * value using the provided {@code CheckedFunction0}.
   *
   * @param <T> The type of the input object and the alternative value generated by the
   *            {@code alternativeSource}.
   * @param $this The input object to be checked for nullity.
   * @param alternativeSource A function that computes an alternative value if the input object
   *                          is null. The function may throw checked exceptions.
   * @return The input object if it is not null; otherwise, the alternative value computed by
   *         the {@code alternativeSource}.
   */
  public <T> T orIfNull(@Nullable T $this,
                        CheckedFunction0<? extends T> alternativeSource) {
    return $this.isNull() ?
        alternativeSource.unchecked().apply()
        : $this;
  }

  /**
   * Applies the provided {@code mapper} function to the input object if it is not null.
   * If the input object is null, an alternative value is computed using the provided
   * {@code alternativeSource}.
   *
   * @param <T> The type of the input object.
   * @param <U> The type of the result after applying the {@code mapper} or the
   *            value generated by {@code alternativeSource}.
   * @param $this The input object to be checked for nullity and processed.
   * @param mapper A function that transforms the input object into a result,
   *               which may throw checked exceptions.
   * @param alternativeSource A function that computes an alternative result if
   *                          the input object is null, which may throw checked exceptions.
   * @return The result of applying {@code mapper} to the input object if it is not null;
   *         otherwise, the result of computing the alternative using {@code alternativeSource}.
   */
  public <T, U> U mapOrIfNull(@Nullable T $this,
                              CheckedFunction1<? super T, ? extends U> mapper,
                              CheckedFunction0<? extends U> alternativeSource) {
    return $this.isNull() ?
        alternativeSource.unchecked().apply()
        : mapper.unchecked().apply($this);
  }
}
