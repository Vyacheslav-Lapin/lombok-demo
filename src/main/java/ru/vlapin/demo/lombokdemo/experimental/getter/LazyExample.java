package ru.vlapin.demo.lombokdemo.experimental.getter;

import lombok.Getter;

import java.util.stream.IntStream;

/**
 * LazyExample.
 */
@SuppressWarnings("java:S125")
public class LazyExample {
//@Getter ???
  @Getter(lazy = true)
  final double[] value = tooExpensiveOperation();
//final AtomicReference<Object> value = new AtomicReference<>();

  // А если инициализация ОЧЕНЬ дорогая, нуждается в
  // синхронизации и если вообще понадобится, то явно не сразу?
  private static double[] tooExpensiveOperation() {
    return IntStream.range(0, 1_000_000)
        .mapToDouble(Math::atan)
        .toArray();
  }

//public double[] getValue() {
//  Object value = this.value.get();
//  if (value == null)
//    synchronized (this.value) {
//      value = this.value.get();
//      if (value == null) {
//        final double[] actualValue = tooExpensiveOperation();
//        value = actualValue == null ? this.value : actualValue;
//        this.value.set(value);
//      }
//    }
//  return (double[]) (value == this.value ? null : value);
//}
}
