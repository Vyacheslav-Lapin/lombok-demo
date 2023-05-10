package ru.vlapin.demo.lombokdemo.common;

import io.vavr.*;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

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
  public <T> CheckedConsumer<T> atFirst(@NotNull CheckedConsumer<? super T> self,
                                        @NotNull CheckedConsumer<? super T> before) {
    return t -> {
      before.accept(t);
      self.accept(t);
    };
  }

  public <T1, T2> CheckedConsumer<T1> compose(@NotNull CheckedConsumer<? super T2> self,
                                              @NotNull Function1<? super T1, ? extends T2> before) {
    return t1 -> self.accept(before.apply(t1));
  }

  public <T1, T2> CheckedConsumer<T1> composeChecked(@NotNull CheckedConsumer<? super T2> self,
                                                     @NotNull CheckedFunction1<? super T1, ? extends T2> before) {
    return t1 -> self.accept(before.apply(t1));
  }

  public static <T> CheckedRunnable supply(@NotNull CheckedConsumer<? super T> self,
                                           @NotNull CheckedFunction0<? extends T> supplier) {
    return () -> self.accept(supplier.apply());
  }

  public <T> CheckedRunnable supply(@NotNull CheckedConsumer<? super T> self, @NotNull T t) {
    return () -> self.accept(t);
  }
}
