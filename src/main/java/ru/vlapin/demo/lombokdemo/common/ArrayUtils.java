package ru.vlapin.demo.lombokdemo.common;

import static java.lang.reflect.Array.*;

import java.util.Arrays;
import java.util.function.IntFunction;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;

/**
 * A utility class providing a set of methods for array manipulation and utility functions
 * extending the native capabilities of Java arrays. The methods in this class are designed
 * to enhance developer productivity by offering features such as generified type handling,
 * type-safe array construction, and element addition.
 *
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
   * Method for adding the possibility to get generified class of an array object.
   * Unlike the native method {@link Object#getClass()}, this method has a convenient contract from a generics
   * perspective — it returns an object of class "Class" with the correct generic type.
   * @param $this array
   * @return object of class "Class" with the correct generic type
   * @param <T> type of the array elements
   */
  public <T> Class<T[]> clazz(T[] $this) {
    return $this.clazz();
  }

  /**
   * Method for suppressing {@link Object#getClass()} method of array objects.
   * Unlike the native method, this method has a convenient contract from a generics perspective - it returns an object of
   * class "Class" with the correct generic type.
   * @param $this array
   * @return object of class "Class" with the correct generic type
   * @param <T> type of the array elements
   */
  public <T> Class<T[]> getClass(T[] $this) {
    return clazz($this);
  }

  /**
   * Determines the component type of the provided array.
   *
   * @param <T> the type of the array elements
   * @param $this the array whose component type is to be determined
   * @return the component type of the array
   */
  @SuppressWarnings("unchecked")
  public <T> Class<T> componentType(T[] $this) {
    return (Class<T>) clazz($this).getComponentType();
  }

  /**
   * Constructs an array of a specified length with the component type derived from the provided array.
   *
   * @param <T> the type of the array elements
   * @param $this the array used to determine the component type
   * @return a function that takes an integer length and returns a new array of the specified length
   */
  @SuppressWarnings("unchecked")
  public <T> IntFunction<T[]> constructor(T... $this) {
    return length -> (T[]) newInstance(componentType($this), length);
  }

  /**
   * Adds an element to the end of the provided array.
   *
   * @param <T> the type of the array elements
   * @param $this the original array to which the element will be added
   * @param additionalElement the element to add to the array
   * @return a new array containing all elements of the original array followed by the additional element
   */
  @SneakyThrows
  public <T> T[] add(T[] $this, T additionalElement) {
    val resultArray = constructor($this).apply($this.length + 1);
    $this.arraycopy(0, resultArray, 0, $this.length);
    resultArray[$this.length] = additionalElement;
    return resultArray;
  }

  /**
   * Adds one or more elements to the end of the provided array and returns a new array containing all elements.
   *
   * @param <T> the type of the array elements
   * @param $this the original array to which the elements will be added
   * @param additionalElements the elements to add to the array
   * @return a new array containing all elements of the original array followed by the additional elements
   */
  @SafeVarargs
  public <T> T[] add(T[] $this, T... additionalElements) {
    val resultArray = constructor($this).apply($this.length + additionalElements.length);
    $this.arraycopy(0, resultArray, 0, $this.length);
    additionalElements.arraycopy(0, resultArray, $this.length, additionalElements.length);
    return resultArray;
  }

  /**
   * Converts a single element into an array containing that element.
   *
   * @param <T> the type of the element
   * @param $this the element to be converted into a single-element array
   * @return an array containing the specified element as its sole component
   */
  @SuppressWarnings({"unchecked", "unused"})
  public <T> T[] toSingleElementArray(T $this) {
    val result = constructor($this).apply(1);
    result[0] = $this;
    return result;
  }

  /**
   * Converts a variable number of elements into an array containing those elements.
   *
   * @param <T> the type of the array elements
   * @param $this the elements to be included in the array
   * @return an array containing the specified elements
   */
  @SafeVarargs
  public <T> T[] toArray(T... $this) {
    return $this;
  }
}
