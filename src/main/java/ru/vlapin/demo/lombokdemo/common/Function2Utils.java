package ru.vlapin.demo.lombokdemo.common;

import io.vavr.Function1;
import io.vavr.Function2;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

@UtilityClass
public class Function2Utils {
  public <V1, V2, R, T> Function2<T, V2, R> compose1(BiFunction<? super V1, ? super V2, ? extends R> self,
                                                     Function<? super T, ? extends V1> before) {
    return (t, v2) -> self.apply(before.apply(t), v2);
  }

  public <V1, V2, R, T> Function2<V1, T, R> compose2(BiFunction<? super V1, ? super V2, ? extends R> self,
                                                     Function<? super T, ? extends V2> before) {
    return (v1, t) -> self.apply(v1, before.apply(t));
  }

  public <K, V, R> Function1<Map.Entry<K, V>, R> m(BiFunction<? super K, ? super V, R> entryMapper) {
    return entry -> entryMapper.apply(entry.getKey(), entry.getValue());
  }
}
