package ru.vlapin.demo.lombokdemo.common;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction1;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ScopeFunctions {

  public <T> void with(T self, CheckedConsumer<? super T> checkedConsumer) {
    checkedConsumer.unchecked().accept(self);
  }

  public <T> T apply(T self, CheckedConsumer<? super T> checkedConsumer) {
    with(self, checkedConsumer);
    return self;
  }

  public <T, R> R map(T self, CheckedFunction1<? super T, ? extends R> mapper) {
    return mapper.unchecked().apply(self);
  }
}
