package ru.vlapin.demo.lombokdemo.common;

import io.vavr.Function0;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

@UtilityClass
public class Function1Utils {

  public <T, R> Function0<? super R> supply(@NotNull Function<? super T, ? extends R> self,
                                            @NotNull T param) {
    return () -> self.apply(param);
  }
}
