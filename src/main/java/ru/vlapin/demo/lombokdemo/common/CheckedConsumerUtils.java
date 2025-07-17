package ru.vlapin.demo.lombokdemo.common;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedFunction1;
import io.vavr.CheckedRunnable;
import lombok.experimental.UtilityClass;

import java.util.function.Function;

@UtilityClass
public class CheckedConsumerUtils {

  /**
   * Returns a chained {@code CheckedConsumer} that first executes {@code before.accept(t)}
   * and then {@code this.accept(t)}, for a given {@code t} of type {@code T}.
   *
   * @param self   the action that will be executed
   * @param before the action that will be executed before this action
   * @return a new {@code CheckedConsumer} that chains {@code before} and {@code this}
   */
  public <T> CheckedConsumer<T> after(CheckedConsumer<? super T> self,
                                      CheckedConsumer<? super T> before) {
    return t -> {
      before.accept(t);
      self.accept(t);
    };
  }

  public <T1, T2> CheckedConsumer<T1> compose(CheckedConsumer<? super T2> self,
                                              Function<? super T1, ? extends T2> before) {
    return t1 -> self.accept(before.apply(t1));
  }

  public <T1, T2> CheckedConsumer<T1> composeChecked(CheckedConsumer<? super T2> self,
                                                     CheckedFunction1<? super T1, ? extends T2> before) {
    return t1 -> self.accept(before.apply(t1));
  }

  public static <T> CheckedRunnable supply(CheckedConsumer<? super T> self,
                                           CheckedFunction0<? extends T> supplier) {
    return () -> self.accept(supplier.apply());
  }

  public <T> CheckedRunnable supply(CheckedConsumer<? super T> self, T t) {
    return () -> self.accept(t);
  }
}
