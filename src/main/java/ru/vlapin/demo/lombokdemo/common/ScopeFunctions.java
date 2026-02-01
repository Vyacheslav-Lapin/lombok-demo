package ru.vlapin.demo.lombokdemo.common;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedFunction1;
import io.vavr.CheckedFunction2;
import io.vavr.CheckedPredicate;
import java.util.Objects;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import org.checkerframework.checker.nullness.qual.Nullable;

@UtilityClass
@ExtensionMethod(value = Objects.class,
                 suppressBaseMethods = false)
public class ScopeFunctions {

  public <T> void with(T $this,
                       CheckedConsumer<? super T> checkedConsumer) {
    checkedConsumer.unchecked().accept($this);
  }

  public <T> T peakWith(T $this,
                        CheckedConsumer<? super T> checkedConsumer) {
    with($this, checkedConsumer);
    return $this;
  }

  public <T, R> R mapWith(T $this,
                          CheckedFunction1<? super T, ? extends R> mapper) {
    return mapper.unchecked().apply($this);
  }

  public <T, U> T doWithIfNotNull(T $this,
                                  @Nullable U operand,
                                  CheckedFunction2<? super T, ? super U, ? extends T> mapper) {
    return operand.isNull() ? $this : mapper.unchecked().apply($this, operand);
  }

  public <T, R> R mapWithIfOrElse(T $this,
                                  CheckedPredicate<? super T> condition,
                                  CheckedFunction1<? super T, ? extends R> mapperIfTrue,
                                  CheckedFunction1<? super T, ? extends R> mapperIfFalse) {
    return mapWith($this,
        condition.unchecked().test($this) ?
            mapperIfTrue
            : mapperIfFalse);
  }

  public <T> T orIfNull(@Nullable T $this,
                        T alternative) {
    return $this.isNull() ? alternative : $this;
  }

  public <T> T orIfNull(@Nullable T $this,
                        CheckedFunction0<? extends T> alternativeSource) {
    return $this.isNull() ?
        alternativeSource.unchecked().apply()
        : $this;
  }

  public <T, U> U mapOrIfNull(@Nullable T $this,
                              CheckedFunction1<? super T, ? extends U> mapper,
                              CheckedFunction0<? extends U> alternativeSource) {
    return $this.isNull() ?
        alternativeSource.unchecked().apply()
        : mapper.unchecked().apply($this);
  }
}
