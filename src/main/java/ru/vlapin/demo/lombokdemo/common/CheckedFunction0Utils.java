package ru.vlapin.demo.lombokdemo.common;

import static io.vavr.API.*;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedRunnable;
import java.util.concurrent.Callable;
import java.util.function.Function;
import lombok.experimental.UtilityClass;

@SuppressWarnings("unused")
@UtilityClass
public class CheckedFunction0Utils {

  @SuppressWarnings("unused")
  public <T> CheckedRunnable toRunnable(CheckedFunction0<? extends T> $this,
                                        CheckedConsumer<? super T> consumer) {
    return () -> consumer.accept($this.apply());
  }

  @SuppressWarnings("unused")
  public Callable<Void> toVoidCallable(CheckedFunction0<?> $this) {
    return toVoidCallable($this, Exception::new);
  }

  public Callable<Void> toVoidCallable(CheckedFunction0<?> $this,
                                       Function<? super Throwable, ? extends Exception> throwableMapper) {
    return () -> {
      Try($this)
          .getOrElseThrow(throwable -> throwable instanceof Exception exception ?
              exception : throwableMapper.apply(throwable));
        return null;
    };
  }
}
