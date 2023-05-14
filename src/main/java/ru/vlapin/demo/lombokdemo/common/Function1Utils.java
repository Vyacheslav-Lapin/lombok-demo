package ru.vlapin.demo.lombokdemo.common;

import io.vavr.Function0;
import lombok.experimental.UtilityClass;

import java.util.function.Function;

@UtilityClass
public class Function1Utils {

  public <T, R> Function0<R> supply(Function<? super T, ? extends R> self, T param) {
    return () -> self.apply(param);
  }
}
