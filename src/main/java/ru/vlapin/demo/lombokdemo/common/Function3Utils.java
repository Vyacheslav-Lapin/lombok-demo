package ru.vlapin.demo.lombokdemo.common;

import io.vavr.Function0;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class Function3Utils {

  public <T1, T2, T3, R> Function0<R> supply(@NotNull io.vavr.Function3<T1, T2, T3, R> self,
                                             T1 t1, T2 t2, T3 t3) {
    return () -> self.apply(t1, t2, t3);
  }
}
