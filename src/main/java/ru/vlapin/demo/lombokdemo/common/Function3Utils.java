package ru.vlapin.demo.lombokdemo.common;

import io.vavr.Function0;
import io.vavr.Function3;
import io.vavr.Lazy;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class Function3Utils {

  public <T1, T2, T3, R> Function0<R> supply(@NotNull Function3<? super T1, ? super T2, ? super T3, ? extends R> self,
                                             T1 t1, T2 t2, T3 t3) {
    return () -> self.apply(t1, t2, t3);
  }

  public <T1, T2, T3, R> Lazy<R> toLazy(@NotNull Function3<? super T1, ? super T2, ? super T3, ? extends R> self,
                                        T1 t1, T2 t2, T3 t3) {
    return Lazy.of(supply(self, t1, t2, t3));
  }
}
