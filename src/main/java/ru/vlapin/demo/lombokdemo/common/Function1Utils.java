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
public class Function1Utils {

  public <T, R> Function0<R> supply(Function<? super T, ? extends R> self, T param) {
    return () -> self.apply(param);
  }

  public <T, R> Function0<R>[] supply(Function<? super T, ? extends R> self, T... params) {
    //noinspection SuspiciousToArrayCall,unchecked
    return params.stream()
        .map(param -> supply(self, param))
        .toArray(value -> (Function0<R>[]) new Function0[value]);
  }

  public <T, R> R[] apply(Function<? super T, ? extends R> self, T... params) {
    //noinspection unchecked
    return Arrays.stream(params)
        .map(self)
        .toArray(length -> (R[]) new Object[length]);
  }
}
