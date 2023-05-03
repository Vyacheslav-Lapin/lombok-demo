package ru.vlapin.demo.lombokdemo.common;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedRunnable;
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
  public <T> CheckedConsumer<T> compose(@NotNull CheckedConsumer<? super T> self,
                                        @NotNull CheckedConsumer<? super T> before) {
    return t -> {
      before.accept(t);
      self.accept(t);
    };
  }

  public static <T> CheckedRunnable composeSupplier(@NotNull CheckedConsumer<? super T> self,
                                                    @NotNull CheckedFunction0<? extends T> supplier) {
    return () -> self.accept(supplier.apply());
  }
}
