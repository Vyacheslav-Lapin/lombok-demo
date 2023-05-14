package ru.vlapin.demo.lombokdemo.common;

import lombok.experimental.UtilityClass;
import lombok.val;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@UtilityClass
public class StreamUtils {

  @SuppressWarnings("unchecked")
  public <T> Stream<T> reverse(Stream<? extends T> input) {
    val temp = (T[]) input.toArray();
    val length = temp.length;

    return IntStream.iterate(length - 1, i -> i - 1)
        .limit(length)
        .mapToObj(i -> temp[i]);
  }
}
