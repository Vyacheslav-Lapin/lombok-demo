package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;

/**
 * A utility class that demonstrates the use of extension methods to enhance and simplify operations
 * on arrays and strings. This class employs the Lombok `@ExtensionMethod` annotation to extend methods
 * from the {@link java.util.Arrays} class and the {@link String} class.
 * <p>
 * Features:
 * - Sorting an array using the `sort` extension method.
 * - Formatting strings using the `format` extension method, which provides an alternative to
 *   `String.format` for better readability.
 * <p>
 * This class is marked with `@UtilityClass` to indicate that it is a utility class containing
 * only static methods and cannot be instantiated.
 * <p>
 * It also applies `@SuppressWarnings` annotations to suppress specific warnings such as
 * `java:S2116` (methods should not call other overridden methods) and `java:S125` (comments
 * containing commented-out code).
 */
@ExtensionMethod({
    java.util.Arrays.class,
    String.class,
})

@UtilityClass
@SuppressWarnings({"java:S2116", "java:S125"})
public class AddExtensionsDemo {

  public int[] getSortedArray(int... ints) {

    val result = ints.clone();

//    result.sort();
    result.sort();

    return result;
  }

  @SuppressWarnings("AccessStaticViaInstance")
  public String greetings(String name) {
    // NOTE! Это не "formatted", а "format" - extension method
    return "Hello, %s!".format(name);
    //return String.format("Hello, %s!", name);
  }
}
