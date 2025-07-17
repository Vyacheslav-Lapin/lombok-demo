package ru.vlapin.demo.lombokdemo.common;

import static java.lang.reflect.Array.*;

import java.util.Arrays;
import java.util.function.IntFunction;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;

/**
 * @see <a href="https://www.javaguides.net/2018/07/java-reflection-for-arrays.html">"Java Reflection for Arrays" by Ramesh Fadatare</a>
 */
@UtilityClass
@ExtensionMethod(suppressBaseMethods = false,
                 value = {
                     Arrays.class,
                     System.class,
                     ReflectionUtils.class,
                 })
public class ArrayUtils {

  /**
   * Method for adding possibility to get generified class of an array object.
   * Unlike the native method {@link Object#getClass()}, this method has a convenient contract from a generics
   * perspective — it returns an object of class "Class" with the correct generic type.
   * @param self array
   * @return
   * @param <T>
   */
  public <T> Class<T[]> clazz(T[] self) {
    return self.clazz();
  }

  /**
   * Method for suppressing {@link Object#getClass()} method of array objects.
   * Unlike the native method, this method has a convenient contract from a generics perspective - it returns an object of
   * class "Class" with the correct generic type.
   * @param self
   * @return
   * @param <T>
   */
  public <T> Class<T[]> getClass(T[] self) {
    return clazz(self);
  }

  /**
   * Determines the component type of the provided array.
   *
   * @param <T> the type of the array elements
   * @param self the array whose component type is to be determined
   * @return the component type of the array
   */
  @SuppressWarnings("unchecked")
  public <T> Class<T> componentType(T[] self) {
    return (Class<T>) clazz(self).getComponentType();
  }

  /**
   * Constructs an array of a specified length with the component type derived from the provided array.
   *
   * @param <T> the type of the array elements
   * @param self the array used to determine the component type
   * @return a function that takes an integer length and returns a new array of the specified length
   */
  @SuppressWarnings("unchecked")
  public <T> IntFunction<T[]> constructor(T... self) {
    return length -> (T[]) newInstance(componentType(self), length);
  }

  /**
   * Adds an element to the end of the provided array.
   *
   * @param <T> the type of the array elements
   * @param self the original array to which the element will be added
   * @param additionalElement the element to add to the array
   * @return a new array containing all elements of the original array followed by the additional element
   */
  @SneakyThrows
  public <T> T[] add(T[] self, T additionalElement) {
    val resultArray = constructor(self).apply(self.length + 1);
    self.arraycopy(0, resultArray, 0, self.length);
    resultArray[self.length] = additionalElement;
    return resultArray;
  }

  /**
   * Adds one or more elements to the end of the provided array and returns a new array containing all elements.
   *
   * @param <T> the type of the array elements
   * @param self the original array to which the elements will be added
   * @param additionalElements the elements to add to the array
   * @return a new array containing all elements of the original array followed by the additional elements
   */
  @SafeVarargs
  public <T> T[] add(T[] self, T... additionalElements) {
    val resultArray = constructor(self).apply(self.length + additionalElements.length);
    self.arraycopy(0, resultArray, 0, self.length);
    additionalElements.arraycopy(0, resultArray, self.length, additionalElements.length);
    return resultArray;
  }

  /**
   * Converts a single element into an array containing that element.
   *
   * @param <T> the type of the element
   * @param self the element to be converted into a single-element array
   * @return an array containing the specified element as its sole component
   */
  @SuppressWarnings("unchecked")
  public <T> T[] toSingleElementArray(T self) {
    val result = constructor(self).apply(1);
    result[0] = self;
    return result;
  }

  /**
   * Converts a variable number of elements into an array containing those elements.
   *
   * @param <T> the type of the array elements
   * @param self the elements to be included in the array
   * @return an array containing the specified elements
   */
  @SafeVarargs
  public <T> T[] toArray(T... self) {
    return self;
  }
}
