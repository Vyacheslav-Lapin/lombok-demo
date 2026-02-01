package ru.vlapin.demo.lombokdemo.common;

import io.vavr.Function0;
import io.vavr.Function3;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Function3Utils {

  public <T1, T2, T3, R> Function0<R> supply(Function3<? super T1, ? super T2, ? super T3, ? extends R> $this,
                                             T1 t1, T2 t2, T3 t3) {
    return () -> $this.apply(t1, t2, t3);
  }
}
