package ru.vlapin.demo.lombokdemo.common;

import static java.lang.reflect.Array.*;

import io.vavr.collection.Array;
import java.util.Arrays;
import java.util.function.IntFunction;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;

/**
 * @see <a href="https://www.javaguides.net/2018/07/java-reflection-for-arrays.html">...</a>
 */
@UtilityClass
@ExtensionMethod(suppressBaseMethods = false,
                 value = {
                     Arrays.class,
                 })
public class ArrayUtils {

  /**
   * Method for adding possibility to get generified class of an array object.
   * Unlike the native method {@link Object#getClass()}, this method has a convenient contract from a generics
   * perspective - it returns an object of class "Class" with the correct generic type.
   * @param self array
   * @return
   * @param <T>
   */
  @SuppressWarnings("unchecked")
  public <T> Class<T[]> toClass(T[] self) {
    return (Class<T[]>) self.getClass();
  }

  /**
   * Method for suppressing {@link Object#getClass()} method of array objects.
   * Unlike the native method, this method has a convenient contract from a generics perspective - it returns an object of
   * class "Class" with the correct generic type.
   * @param self
   * @return
   * @param <T>
   */
  @SuppressWarnings("unchecked")
  public <T> Class<T[]> getClass(T[] self) {
    return (Class<T[]>) self.getClass();
  }

  @SuppressWarnings("unchecked")
  public <T> Class<T> componentType(T[] self) {
    return (Class<T>) toClass(self).getComponentType();
  }

  @SuppressWarnings("unchecked")
  public <T> IntFunction<T[]> constructor(T[] self) {
    return length -> (T[]) newInstance(componentType(self), length);
  }

  @SneakyThrows
  public <T> T[] add(T[] self, T additionalElement) {
    return Array.of(self)
                .append(additionalElement)
                .toJavaArray(constructor(self));
  }

  @SafeVarargs
  public <T> T[] add(T[] self, T... additionalElements) {
    return Array.of(self)
                .appendAll(additionalElements.stream()::iterator)
                .toJavaArray(constructor(self));
  }
}
