package ru.vlapin.demo.lombokdemo.common;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class StreamUtils {

  @SuppressWarnings("unchecked")
  public <T> Stream<T> reverse(@NotNull Stream<T> input) {
    val temp = input.toArray();
    return (Stream<T>) IntStream.range(0, temp.length)
                           .mapToObj(i -> temp[temp.length - i - 1]);
  }
}
