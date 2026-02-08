package ru.vlapin.demo.lombokdemo.common;

import lombok.experimental.UtilityClass;
import lombok.val;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Utility class providing helper methods for working with Java Streams.
 */
@UtilityClass
public class StreamUtils {

  /**
   * Reverses the order of elements in the provided {@link Stream}.
   *
   * @param <T>   the type of elements in the input stream
   * @param input the input stream whose elements need to be reversed
   * @return a new stream with the elements of the input stream in reverse order
   */
  @SuppressWarnings("unchecked")
  public <T> Stream<T> reverse(Stream<? extends T> input) {
    val temp = (T[]) input.toArray();
    val length = temp.length;

    return IntStream.iterate(length - 1, i -> i - 1)
        .limit(length)
        .mapToObj(i -> temp[i]);
  }
}
