package ru.vlapin.demo.lombokdemo.common;

import lombok.experimental.UtilityClass;

import java.util.function.*;

/**
 * PredicateUtils.
 */
@UtilityClass
public class PredicateUtils {

  public <T1, T2> Predicate<T2> compose(Predicate<? super T1> $this,
                                        Function<? super T2, ? extends T1> before) {
    return t -> $this.test(before.apply(t));
  }

  public <T> Predicate<T> compose(IntPredicate $this,
                                  ToIntFunction<? super T> before) {
    return t -> $this.test(before.applyAsInt(t));
  }

  public <T> Predicate<T> compose(LongPredicate $this,
                                  ToLongFunction<? super T> before) {
    return t -> $this.test(before.applyAsLong(t));
  }

  public <T> Predicate<T> compose(DoublePredicate $this,
                                  ToDoubleFunction<? super T> before) {
    return t -> $this.test(before.applyAsDouble(t));
  }

  public <T1, T2> Function<T1, T2> andThen(Predicate<? super T1> $this,
                                           Function<Boolean, ? extends T2> after) {
    return t1 -> after.apply($this.test(t1));
  }

  public <T> IntFunction<T> andThen(IntPredicate $this,
                                    Function<Boolean, ? extends T> after) {
    return i -> after.apply($this.test(i));
  }

  public <T> LongFunction<T> andThen(LongPredicate $this,
                                     Function<Boolean, ? extends T> after) {
    return l -> after.apply($this.test(l));
  }

  public <T> DoubleFunction<T> andThen(DoublePredicate $this,
                                       Function<Boolean, ? extends T> after) {
    return v -> after.apply($this.test(v));
  }
}
