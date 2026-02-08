package ru.vlapin.demo.lombokdemo.common;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
import static java.util.Comparator.*;

import io.vavr.CheckedFunction1;
import io.vavr.Function1;
import io.vavr.control.Option;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;
import java.util.function.UnaryOperator;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.StandardException;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.springframework.core.annotation.AnnotatedElementUtils;

/**
 * Utility class for managing properties and creating object instances based on
 * properties files. PropertiesUtils provides methods to bind properties to object
 * fields by resolving appropriate constructor parameters.
 * <p>
 * This class uses {@link InitProperties} annotation to specify properties
 * file names and provides mechanisms for parsing and resolving properties
 * directly to objects.
 * <p>
 * The utility simplifies the instantiation of classes, taking into account
 * dependencies defined as constructor parameters.
 */
@UtilityClass
@ExtensionMethod(value = {
    Arrays.class,
    InputStreamUtils.class,
    AnnotatedElementUtils.class,
}, suppressBaseMethods = false)
public class PropertiesUtils {

  /**
   * Retrieves an instance of the specified class by loading properties based on the
   * provided class's {@link InitProperties} annotation or its simple name.
   *
   * @param <T> the type of the object to be instantiated
   * @param tClass the class of the object to be instantiated. If annotated with {@link InitProperties},
   *               the annotation's value will be used to determine the properties file name. Otherwise,
   *               the simple name of the class will be used.
   * @return an instance of the provided class, populated based on the resolved properties.
   */
  public <T> T getInstance(Class<? extends T> tClass) {

    val propertiesFileName = Option.of(tClass.findMergedAnnotation(InitProperties.class))
        .map(InitProperties::value)
        .getOrElse(tClass::getSimpleName);

    return from(tClass, propertiesFileName);
  }

  /**
   * Instantiates an object of the specified class by using the properties defined
   * in the specified properties file to populate the values for the class's constructor.
   * The constructor with the maximum number of parameters in the given class is used
   * for instantiation.
   *
   * @param <T> the type of the object to be instantiated
   * @param tClass the class to create an instance of
   * @param propertiesFileName the name of the properties file containing values
   *                           for the constructor parameters
   * @return an instance of the specified class, populated with the properties
   *         from the specified file
   */
  public <T> T from(Class<? extends T> tClass, String propertiesFileName) {
    return from(
        parseProperties(propertiesFileName)::apply,
        getMaxArgsCountConstructor(tClass));
  }

  /**
   * Creates an instance of a class using a specified constructor and properties file.
   * The method loads properties from the specified file and uses the values to populate
   * the parameters of the provided constructor.
   *
   * @param <T> the type of the object to be created
   * @param constructor the constructor to be used for instantiating the object
   * @param propertiesFileName the name of the properties file containing values to be
   *                           used for constructor parameters
   * @return an instance of the specified type, created and populated with values
   *         from the properties file
   */
  public <T> T from(Constructor<? extends T> constructor, String propertiesFileName) {
    return from(parseProperties(propertiesFileName)::apply, constructor);
  }

  /**
   * Creates an instance of the specified class by utilizing the properties provided through
   * the specified {@code getProperty} function. The constructor of the class, having the
   * maximum number of parameters, is used for instantiation.
   *
   * @param <T> the type of the object to be created
   * @param tClass the class of the object to be instantiated
   * @param getProperty a function that maps property names to their corresponding values
   * @return an instance of the specified class populated using the provided properties
   */
  public <T> T from(Class<? extends T> tClass, UnaryOperator<String> getProperty) {
    return from(getProperty, getMaxArgsCountConstructor(tClass));
  }

  /**
   * Creates an instance of the specified type by populating the constructor's parameters
   * with the values resolved using the supplied {@code getProperty} function.
   *
   * @param <T> the type of the object to be created.
   * @param getProperty a function that maps property names to their corresponding values.
   * @param constructor the constructor to be used for instantiation.
   * @return an instance of the specified type, created and populated with values
   *         resolved using the {@code getProperty} function.
   */
  public <T> T from(UnaryOperator<String> getProperty, Constructor<? extends T> constructor) {
    return CheckedFunction1.<Object[], T>of(constructor::newInstance).unchecked().apply(
        constructor.getParameters().stream()
            .map(parameter -> resolveParameter(getProperty, parameter))
            .toArray());
  }

  /**
   * Parses the specified properties file and creates a function to retrieve property values by their keys.
   *
   * @param propertiesFileName the name of the properties file to be loaded
   * @return a function that takes a property key as input and returns the corresponding property value
   */
  public Function1<String, String> parseProperties(String propertiesFileName) {
    val properties = new Properties();
    "%s.properties"
        .formatted(propertiesFileName)
        .withFileInputStreamChecked(properties::load);
    return properties::getProperty;
  }

  /**
   * Retrieves the constructor of the specified class that has the maximum number of parameters.
   * This method uses reflection to analyze the constructors of the given class and selects the one
   * with the highest parameter count. If no constructors are found, a {@code PropsBinderException}
   * is thrown.
   *
   * @param <T> the type of the class for which the constructor will be determined
   * @param tClass the class whose constructor with the maximum number of parameters is to be retrieved
   * @return the constructor of the specified class with the maximum number of parameters
   * @throws PropsBinderException if the class has no constructors
   */
  @SuppressWarnings("unchecked")
  public <T> Constructor<T> getMaxArgsCountConstructor(Class<? extends T> tClass) {
    Comparator<Constructor<T>> comparator = comparingInt(Constructor::getParameterCount);
    return ((Constructor<T>[]) tClass.getConstructors()).stream()
                                                        .max(comparator)
                                                        .orElseThrow(() -> new PropsBinderException("Нет ни одного конструктора!"));
  }

  /**
   * Resolves and provides a value for the specified {@link Parameter} using the given
   * mapping function to retrieve property values.
   *
   * @param <T> the type of the parameter to resolve.
   * @param getValue a function that maps property names to their corresponding values.
   * @param parameter the {@link Parameter} whose value needs to be resolved.
   * @return the resolved parameter value of type {@code T}.
   */
  @SuppressWarnings("unchecked")
  public <T> T resolveParameter(UnaryOperator<String> getValue, Parameter parameter) {
    return resolveParameter(getValue, parameter.getName(), (Class<? extends T>) parameter.getType());
  }

  /**
   * Resolves parameter based on type using provided value
   * Resolves and converts a parameter value to the specified type using the provided
   * value-mapping function. Supports primitive types, wrapper types, strings, enums,
   * and other custom objects.
   *
   * @param <T> the type of the parameter to resolve.
   * @param getValue a function that takes a property name as input and maps it to its corresponding value.
   * @param name the name of the property to be resolved.
   * @param type the {@code Class} object representing the expected type of the resolved parameter.
   * @return the resolved parameter value, cast to the specified type {@code T}.
   */
  @SuppressWarnings("unchecked")
  public <T> T resolveParameter(UnaryOperator<String> getValue, String name, Class<? extends T> type) {
    // Resolves parameter based on type via a switch statement
    return (T) switch (type) {
      case Class<? extends T> t when t == String.class -> getValue.apply(name);
      case Class<? extends T> t when t.isEnum() -> toEnumValue(getValue.apply(name), t);
      case Class<? extends T> t when t == int.class || t == Integer.class -> Integer.valueOf(getValue.apply(name));
      case Class<? extends T> t when t == boolean.class || t == Boolean.class -> Boolean.valueOf(getValue.apply(name));
      case Class<? extends T> t when t == double.class || t == Double.class -> Double.valueOf(getValue.apply(name));
      case Class<? extends T> t when t == long.class || t == Long.class -> Long.valueOf(getValue.apply(name));
      case Class<? extends T> t when t == char.class || t == Character.class -> getValue.apply(name).charAt(0);
      case Class<? extends T> t when t == float.class || t == Float.class -> Float.valueOf(getValue.apply(name));
      case Class<? extends T> t when t == short.class || t == Short.class -> Short.valueOf(getValue.apply(name));
      case Class<? extends T> t when t == byte.class || t == Byte.class -> Byte.valueOf(getValue.apply(name));
      default -> resolveObjectParameter(getValue, type, name);
    };
  }

  /**
   * Resolves object parameter from properties using reflection.
   * Converts a string value to its corresponding enum constant based on the specified enum class.
   *
   * @param <T>   the type of the enum.
   * @param s     the string representation of the enum constant.
   * @param aClass the class object of the enum type to which the string should be converted.
   * @return the enum constant of the specified type corresponding to the provided string.
   * @throws IllegalArgumentException if the specified string does not match any enum constant
   *                                  in the specified enum class.
   * @throws NullPointerException if either the provided string or the enum class is null.
   */
  @SuppressWarnings("unchecked")
  private <T extends Enum<T>> T toEnumValue(String s, Class<?> aClass) {
    return Enum.valueOf((Class<T>) aClass, s);
  }

  /**
   * Resolves object parameter from properties using reflection
   */
  private <T> T resolveObjectParameter(UnaryOperator<String> getProperty, Class<? extends T> type, String prefix) {
    if (type.isInterface() || Modifier.isAbstract(type.getModifiers()))
      throw new PropsBinderException("Type must not be an interface or abstract class!");
    return from(type, s -> getProperty.apply("%s.%s".formatted(prefix, s)));
  }

  /**
   * Annotation to specify initialization properties for a given class.
   * This annotation is used to associate a class with a specific properties file
   * that should be used to populate its fields or constructor parameters.
   */
  @Target(TYPE)
  @Retention(RUNTIME)
  public @interface InitProperties {
    String value();
  }
}

/**
 * This exception represents issues encountered during the property binding process.
 * <p>
 * PropsBinderException is a runtime exception specifically designed to handle errors
 * related to binding properties in the application. This may include invalid or incorrect
 * configurations, missing required values, or any binding-related processing error.
 * <p>
 * By extending {@link RuntimeException}, this exception can be programmatically
 * used to propagate property binding issues up the call stack without requiring
 * explicit handling by the caller.
 */
@StandardException
class PropsBinderException extends RuntimeException {
}
