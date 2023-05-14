package ru.vlapin.demo.lombokdemo.common;

import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

@UtilityClass
@ExtensionMethod({
    Arrays.class,
})
public class Function0Utils {

  @SafeVarargs
  public <T> Runnable toRunnable(Supplier<? extends T> self, Consumer<? super T>... consumers) {
    return () -> consumers.stream()
        .forEach(Consumer2.<Consumer<? super T>, T>of(Consumer::accept)
            .reversed()
            .accept(self.get()));
  }
}
