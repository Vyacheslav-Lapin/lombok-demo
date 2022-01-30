package ru.vlapin.demo.lombokdemo.common;

import io.vavr.Function2;
import java.util.function.Function;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class FunctionUtils {

  public <V1, V2, R, T> Function2<T, V2, R> compose1(@NotNull Function2<V1, V2, R> self,
                                                     @NotNull Function<T, V1> before) {
    return (t, v2) -> self.apply(before.apply(t), v2);
  }

  public <V1, V2, R, T> Function2<V1, T, R> compose2(@NotNull Function2<V1, V2, R> self,
                                                     @NotNull Function<T, V2> before) {
    return (v1, t) -> self.apply(v1, before.apply(t));
  }
}
