package ru.vlapin.demo.lombokdemo.common;

import io.vavr.Function2;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.Function;

@UtilityClass
public class Function2Utils {

    public <V1, V2, R, T> Function2<T, V2, R> compose1(@NotNull BiFunction<V1, V2, R> self,
                                                       @NotNull Function<T, V1> before) {
        return (t, v2) -> self.apply(before.apply(t), v2);
    }

    public <V1, V2, R, T> Function2<V1, T, R> compose2(@NotNull BiFunction<V1, V2, R> self,
                                                       @NotNull Function<T, V2> before) {
        return (v1, t) -> self.apply(v1, before.apply(t));
    }
}
