package ru.vlapin.demo.lombokdemo.common;

import io.vavr.CheckedFunction0;
import java.util.concurrent.Callable;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CallableUtils {

  @SuppressWarnings({"unchecked", "unused", "java:S100"})
  public <T> Callable<T> Callable(Callable<? extends T> callable) {
    return (Callable<T>) callable;
  }

  @SuppressWarnings("unchecked")
  public <T> Callable<T> of(Callable<? extends T> callable) {
    return (Callable<T>) callable;
  }

  public <T> CheckedFunction0<T> toChecked(Callable<? extends T> callable) {
    return callable::call;
  }
}
