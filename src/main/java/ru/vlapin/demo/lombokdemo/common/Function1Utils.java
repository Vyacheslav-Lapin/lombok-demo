package ru.vlapin.demo.lombokdemo.common;

import io.vavr.Function0;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.function.Function;

@UtilityClass
@ExtensionMethod({
    Arrays.class,
})
@SuppressWarnings("unused")
public class Function1Utils {

  public <T, R> Function0<R> supply(Function<? super T, ? extends R> self, T param) {
    return () -> self.apply(param);
  }

  @SafeVarargs
  @SuppressWarnings({"SuspiciousToArrayCall", "unchecked"})
  public <T, R> Function0<R>[] supply(Function<? super T, ? extends R> self, T... params) {
    return params.stream()
        .map(param -> supply(self, param))
        .toArray(value -> (Function0<R>[]) new Function0[value]);
  }

  @SafeVarargs
  @SuppressWarnings("unchecked")
  public <T, R> R[] apply(Function<? super T, ? extends R> self, T... params) {
    return Arrays.stream(params)
        .map(self)
        .toArray(length -> (R[]) new Object[length]);
  }
}
