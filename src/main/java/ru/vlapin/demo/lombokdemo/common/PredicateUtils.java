package ru.vlapin.demo.lombokdemo.common;

import lombok.experimental.UtilityClass;

import java.util.function.*;

/**
 * PredicateUtils.
 */
@UtilityClass
public class PredicateUtils {

  public <T1, T2> Predicate<T2> compose(Predicate<? super T1> self,
                                        Function<? super T2, ? extends T1> before) {
    return t -> self.test(before.apply(t));
  }

  public <T> Predicate<T> compose(IntPredicate self,
                                  ToIntFunction<? super T> before) {
    return t -> self.test(before.applyAsInt(t));
  }

  public <T> Predicate<T> compose(LongPredicate self,
                                  ToLongFunction<? super T> before) {
    return t -> self.test(before.applyAsLong(t));
  }

  public <T> Predicate<T> compose(DoublePredicate self,
                                  ToDoubleFunction<? super T> before) {
    return t -> self.test(before.applyAsDouble(t));
  }

  public <T1, T2> Function<T1, T2> andThen(Predicate<? super T1> self,
                                           Function<Boolean, ? extends T2> after) {
    return t1 -> after.apply(self.test(t1));
  }

  public <T> IntFunction<T> andThen(IntPredicate self,
                                    Function<Boolean, ? extends T> after) {
    return i -> after.apply(self.test(i));
  }

  public <T> LongFunction<T> andThen(LongPredicate self,
                                     Function<Boolean, ? extends T> after) {
    return l -> after.apply(self.test(l));
  }

  public <T> DoubleFunction<T> andThen(DoublePredicate self,
                                       Function<Boolean, ? extends T> after) {
    return v -> after.apply(self.test(v));
  }
}
