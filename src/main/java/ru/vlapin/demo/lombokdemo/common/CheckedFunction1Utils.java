package ru.vlapin.demo.lombokdemo.common;

import io.vavr.CheckedFunction0;
import io.vavr.CheckedFunction1;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class CheckedFunction1Utils {

  public <T, R> CheckedFunction0<? super R> supply(@NotNull CheckedFunction1<? super T, ? extends R> self,
                                                   @NotNull T param) {
    return () -> self.apply(param);
  }
}
