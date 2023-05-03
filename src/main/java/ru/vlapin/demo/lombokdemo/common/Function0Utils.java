package ru.vlapin.demo.lombokdemo.common;

import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.util.Lazy;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

@UtilityClass
@ExtensionMethod({
    Arrays.class,
})
public class Function0Utils {

  public <T> Runnable toRunnable(@NotNull Supplier<? extends T> self,
                                 @NotNull Consumer<? super T>... consumers) {
    return toRunnable(toLazy(self), consumers);
  }

  public <T> Runnable toRunnable(@NotNull Lazy<? extends T> self,
                                 @NotNull Consumer<? super T>... consumers) {

    val accept = Consumer2.<Consumer<? super T>, T>narrow(Consumer::accept)
        .reversed()
        .accept(self.get());

    return () -> consumers.stream()
        .forEach(accept);
  }

  public <T> Lazy<T> toLazy(@NotNull Supplier<? extends T> self) {
    return Lazy.of(self);
  }
}
