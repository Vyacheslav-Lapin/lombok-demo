package ru.vlapin.demo.lombokdemo.common;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedRunnable;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class CheckedFunction0Utils {
  public <T> CheckedRunnable toRunnable(@NotNull CheckedFunction0<? extends T> self,
                                        @NotNull CheckedConsumer<? super T> consumer) {
    return () -> consumer.accept(self.apply());
  }
}
