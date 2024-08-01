package ru.vlapin.demo.lombokdemo.common;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedRunnable;
import io.vavr.control.Try;
import java.util.concurrent.Callable;
import java.util.function.Function;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CheckedFunction0Utils {

  public <T> CheckedRunnable toRunnable(CheckedFunction0<? extends T> self,
                                        CheckedConsumer<? super T> consumer) {
    return () -> consumer.accept(self.apply());
  }

  public Callable<Void> toVoidCallable(CheckedFunction0<?> checkedFunction) {
    return toVoidCallable(checkedFunction, Exception::new);
  }

  @SuppressWarnings("DataFlowIssue")
  public Callable<Void> toVoidCallable(CheckedFunction0<?> checkedFunction,
                                       Function<? super Throwable, ? extends Exception> throwableMapper) {
    return () -> {
        Try.of(checkedFunction)
           .getOrElseThrow(throwable -> throwable instanceof Exception exception ?
               exception : throwableMapper.apply(throwable));
        return null;
    };
  }
}
