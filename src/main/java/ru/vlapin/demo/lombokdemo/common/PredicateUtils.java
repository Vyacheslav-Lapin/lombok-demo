package ru.vlapin.demo.lombokdemo.common;

import lombok.experimental.UtilityClass;

import java.util.function.*;

/**
 * Utility class providing methods to compose and transform predicates and functions.
 */
@UtilityClass
public class PredicateUtils {

  /**
   * Composes a predicate with a function to create a new predicate.
   * The resulting predicate evaluates the function on the input argument
   * and then applies the original predicate to the result.
   *
   * @param <T1> the type of the input to the original predicate
   * @param <T2> the type of the input to the resulting predicate
   * @param $this the original predicate to be applied after the function
   * @param before a function to be applied to the input of the resulting predicate
   *               before passing it to the original predicate
   * @return a predicate that first applies the {@code before} function to its input
   *         and then applies the {@code $this} predicate to the result
   */
  public <T1, T2> Predicate<T2> compose(Predicate<? super T1> $this,
                                        Function<? super T2, ? extends T1> before) {
    return t -> $this.test(before.apply(t));
  }

  /**
   * Composes an {@link IntPredicate} with a {@link ToIntFunction} to create a new {@link Predicate}.
   * The resulting predicate evaluates the {@code before} function on the input argument, applies the
   * {@code $this} {@link IntPredicate} to the result, and returns the boolean outcome.
   *
   * @param <T> the type of the input to the resulting predicate
   * @param $this the {@link IntPredicate} to be applied after converting the input
   * @param before a {@link ToIntFunction} that converts the input of type {@code T}
   *               to an {@code int} before passing it to the {@code $this} predicate
   * @return a {@link Predicate} that applies the {@code before} function to the input
   *         and then evaluates the {@code $this} predicate on the resulting {@code int}
   */
  public <T> Predicate<T> compose(IntPredicate $this,
                                  ToIntFunction<? super T> before) {
    return t -> $this.test(before.applyAsInt(t));
  }

  /**
   * Composes a {@link LongPredicate} with a {@link ToLongFunction} to create a new {@link Predicate}.
   * The resulting predicate evaluates the {@code before} function on the input argument,
   * applies the {@code $this} {@link LongPredicate} to the result, and returns the boolean outcome.
   *
   * @param <T> the type of the input to the resulting predicate
   * @param $this the {@link LongPredicate} to be applied after converting the input
   * @param before a {@link ToLongFunction} that converts the input of type {@code T}
   *               to a {@code long} before passing it to the {@code $this} predicate
   * @return a {@link Predicate} that evaluates the {@code before} function on its input
   *         and then applies the {@code $this} predicate to the resulting {@code long}
   */
  public <T> Predicate<T> compose(LongPredicate $this,
                                  ToLongFunction<? super T> before) {
    return t -> $this.test(before.applyAsLong(t));
  }

  /**
   * Composes a {@link DoublePredicate} with a {@link ToDoubleFunction} to create a new {@link Predicate}.
   * The resulting predicate evaluates the {@code before} function on the input argument,
   * applies the {@code $this} {@link DoublePredicate} to the result, and returns the boolean outcome.
   *
   * @param <T> the type of the input to the resulting predicate
   * @param $this the {@link DoublePredicate} to be applied after converting the input
   * @param before a {@link ToDoubleFunction} that converts the input of type {@code T}
   *               to a {@code double} before passing it to the {@code $this} predicate
   * @return a {@link Predicate} that applies the {@code before} function to the input
   *         and then evaluates the {@code $this} predicate on the resulting {@code double}
   */
  public <T> Predicate<T> compose(DoublePredicate $this,
                                  ToDoubleFunction<? super T> before) {
    return t -> $this.test(before.applyAsDouble(t));
  }

  /**
   * Composes a {@link Predicate} with a {@link Function} to create a new {@link Function}.
   * The resulting function applies the given predicate to evaluate its input and then
   * applies the provided function to the boolean result of the predicate.
   *
   * @param <T1> the type of the input to the predicate and the resulting function
   * @param <T2> the type of the output of the resulting function
   * @param $this the predicate to be evaluated on the input
   * @param after the function to be applied to the boolean result of the predicate
   * @return a function that applies the {@code $this} predicate to its input and
   *         then applies the {@code after} function to the result
   */
  public <T1, T2> Function<T1, T2> andThen(Predicate<? super T1> $this,
                                           Function<Boolean, ? extends T2> after) {
    return t1 -> after.apply($this.test(t1));
  }

  /**
   * Creates an {@link IntFunction} by composing an {@link IntPredicate} with a {@link Function}.
   * The resulting function evaluates the predicate on the input integer and then applies
   * the given function to the boolean result of the predicate.
   *
   * @param <T> the type of the output of the resulting function
   * @param $this the {@link IntPredicate} to be evaluated on the input integer
   * @param after the {@link Function} to be applied to the boolean result of the {@code $this} predicate
   * @return an {@link IntFunction} that applies the {@code $this} predicate to its input integer and
   *         then applies the {@code after} function to the boolean result
   */
  public <T> IntFunction<T> andThen(IntPredicate $this,
                                    Function<Boolean, ? extends T> after) {
    return i -> after.apply($this.test(i));
  }

  /**
   * Creates a {@link LongFunction} by composing a {@link LongPredicate} with a {@link Function}.
   * The resulting function evaluates the predicate on the input-long value and then applies
   * the given function to the boolean result of the predicate.
   *
   * @param <T> the type of the output of the resulting function
   * @param $this the {@link LongPredicate} to be evaluated on the input-long value
   * @param after the {@link Function} to be applied to the boolean result of the {@code $this} predicate
   * @return a {@link LongFunction} that applies the {@code $this} predicate to its input-long value and
   *         then applies the {@code after} function to the boolean result
   */
  public <T> LongFunction<T> andThen(LongPredicate $this,
                                     Function<Boolean, ? extends T> after) {
    return l -> after.apply($this.test(l));
  }

  /**
   * Creates a {@link DoubleFunction} by composing a {@link DoublePredicate} with a {@link Function}.
   * The resulting function evaluates the predicate on the input double value and then applies
   * the given function to the boolean result of the predicate.
   *
   * @param <T> the type of the output of the resulting function
   * @param $this the {@link DoublePredicate} to be evaluated on the input double value
   * @param after the {@link Function} to be applied to the boolean result of the {@code $this} predicate
   * @return a {@link DoubleFunction} that applies the {@code $this} predicate to its input double value and
   *         then applies the {@code after} function to the boolean result
   */
  public <T> DoubleFunction<T> andThen(DoublePredicate $this,
                                       Function<Boolean, ? extends T> after) {
    return v -> after.apply($this.test(v));
  }
}
