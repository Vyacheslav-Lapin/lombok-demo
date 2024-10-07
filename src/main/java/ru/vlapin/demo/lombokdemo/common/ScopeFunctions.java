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

  public <T> void with(T self,
                       CheckedConsumer<? super T> checkedConsumer) {
    checkedConsumer.unchecked().accept(self);
  }

  public <T> T peakWith(T self,
                        CheckedConsumer<? super T> checkedConsumer) {
    with(self, checkedConsumer);
    return self;
  }

  public <T, R> R mapWith(T self,
                          CheckedFunction1<? super T, ? extends R> mapper) {
    return mapper.unchecked().apply(self);
  }

  public <T, U> T doWithIfNotNull(T self,
                                  @Nullable U operand,
                                  CheckedFunction2<? super T, ? super U, ? extends T> mapper) {
    return operand.isNull() ? self : mapper.unchecked().apply(self, operand);
  }

  public <T, R> R mapWithIfOrElse(T self,
                                  CheckedPredicate<? super T> condition,
                                  CheckedFunction1<? super T, ? extends R> mapperIfTrue,
                                  CheckedFunction1<? super T, ? extends R> mapperIfFalse) {
    return mapWith(self,
        condition.unchecked().test(self) ?
            mapperIfTrue
            : mapperIfFalse);
  }

  public <T> T orIfNull(@Nullable T self,
                        T alternative) {
    return self.isNull() ? alternative : self;
  }

  public <T> T orIfNull(@Nullable T self,
                        CheckedFunction0<? extends T> alternativeSource) {
    return self.isNull() ?
        alternativeSource.unchecked().apply()
        : self;
  }

  public <T, U> U mapOrIfNull(@Nullable T self,
                              CheckedFunction1<? super T, ? extends U> mapper,
                              CheckedFunction0<? extends U> alternativeSource) {
    return self.isNull() ?
        alternativeSource.unchecked().apply()
        : mapper.unchecked().apply(self);
  }
}
