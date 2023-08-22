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
}
