package ru.vlapin.demo.lombokdemo.common;

import io.vavr.CheckedFunction0;
import io.vavr.CheckedFunction1;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CheckedFunction1Utils {

  public <T, R> CheckedFunction0<R> supply(CheckedFunction1<? super T, ? extends R> $this, T param) {
    return () -> $this.apply(param);
  }
}
