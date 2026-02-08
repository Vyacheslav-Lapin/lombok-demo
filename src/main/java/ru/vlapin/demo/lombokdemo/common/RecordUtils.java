package ru.vlapin.demo.lombokdemo.common;

import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;

import java.lang.reflect.Constructor;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;

/**
 * A utility class for working with Java Record types.
 * Provides methods for interacting with Records, such as retrieving
 * the canonical constructor of a given Record class.
 */
@UtilityClass
@SuppressWarnings("unused")
@ExtensionMethod(value = {
    Arrays.class,
}, suppressBaseMethods = false)
public class RecordUtils {

  /**
   * Retrieves the canonical constructor of the specified Record class.
   * The canonical constructor is defined as the constructor whose parameters
   * correspond to all the components of the Record, in the exact order they are declared.
   *
   * @param <T>        the Record type
   * @param recordClass the {@code Class} object representing the Record type
   * @return the canonical constructor of the specified Record class
   * @throws NoSuchMethodException if a matching method is not found
   * @throws SecurityException     if access to the constructor is denied
   */
  @SneakyThrows
  @SuppressWarnings("JavadocDeclaration")
  public <T extends Record> Constructor<T> canonicalConstructor(Class<T> recordClass) {
    val parameterTypes = recordClass.getRecordComponents().stream()
        .map(RecordComponent::getType)
        .toArray(Class<?>[]::new);
    return recordClass.getDeclaredConstructor(parameterTypes);
  }
}
