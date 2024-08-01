package ru.vlapin.demo.lombokdemo.common;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedFunction1;
import io.vavr.CheckedPredicate;
import java.util.Objects;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@UtilityClass
@ExtensionMethod(value = Objects.class,
                 suppressBaseMethods = false)
public class ScopeFunctions {

  public <T> void with(@NonNull T self,
                       @NonNull CheckedConsumer<@NonNull ? super T> checkedConsumer) {
    checkedConsumer.unchecked().accept(self);
  }

  public <T> T peakWith(@NonNull T self,
                        @NonNull CheckedConsumer<@NonNull ? super T> checkedConsumer) {
    with(self, checkedConsumer);
    return self;
  }

  public <T, R> R mapWith(@NonNull T self,
                          @NonNull CheckedFunction1<@NonNull ? super T, @NonNull ? extends R> mapper) {
    return mapper.unchecked().apply(self);
  }

  public <T, R> R mapWithIfOrElse(@NonNull T self,
                                  @NonNull CheckedPredicate<@NonNull ? super T> condition,
                                  @NonNull CheckedFunction1<@NonNull ? super T, @NonNull ? extends R> mapperIfTrue,
                                  @NonNull CheckedFunction1<@NonNull ? super T, @NonNull ? extends R> mapperIfFalse) {
    return mapWith(self,
        condition.unchecked().test(self) ?
            mapperIfTrue
            : mapperIfFalse);
  }

  @SuppressWarnings("DataFlowIssue")
  public <T> T orIfNull(@Nullable T self,
                        @NonNull T alternative) {
    return self.isNull() ? alternative : self;
  }

  @SuppressWarnings("DataFlowIssue")
  public <T> T orIfNull(@Nullable T self,
                        @NonNull CheckedFunction0<@NonNull ? extends T> alternativeSource) {
    return self.isNull() ?
        alternativeSource.unchecked().apply()
        : self;
  }

  public <T, U> U mapOrIfNull(@Nullable T self,
                              @NonNull CheckedFunction1<@NonNull ? super T, @NonNull ? extends U> mapper,
                              @NonNull CheckedFunction0<@NonNull ? extends U> alternativeSource) {
    return self.isNull() ?
        alternativeSource.unchecked().apply()
        : mapper.unchecked().apply(self);
  }
}
