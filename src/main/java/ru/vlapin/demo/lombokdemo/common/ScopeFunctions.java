package ru.vlapin.demo.lombokdemo.common;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction1;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class ScopeFunctions {

  public <T> void with(@NotNull T self,
                       @NotNull CheckedConsumer<? super T> checkedConsumer) {
    checkedConsumer.unchecked().accept(self);
  }

  public <T> T peakWith(@NotNull T self,
                        @NotNull CheckedConsumer<? super T> checkedConsumer) {
    with(self, checkedConsumer);
    return self;
  }

  public <T, R> R mapWith(@NotNull T self,
                          @NotNull CheckedFunction1<? super T, ? extends R> mapper) {
    return mapper.unchecked().apply(self);
  }
}
