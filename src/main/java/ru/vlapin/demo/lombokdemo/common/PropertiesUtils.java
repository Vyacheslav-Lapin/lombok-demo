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

@UtilityClass
@ExtensionMethod({
    Arrays.class,
    InputStreamUtils.class,
    AnnotatedElementUtils.class,
})
public class PropertiesUtils {

  public <T> T getInstance(Class<? extends T> tClass) {

    val propertiesFileName = Option.of(tClass.findMergedAnnotation(InitProperties.class))
        .map(InitProperties::value)
        .getOrElse(tClass::getSimpleName);

    return from(tClass, propertiesFileName);
  }

  public <T> T from(Class<? extends T> tClass, String propertiesFileName) {
    return from(
        parseProperties(propertiesFileName)::apply,
        getMaxArgsCountConstructor(tClass));
  }

  public <T> T from(Constructor<? extends T> constructor, String propertiesFileName) {
    return from(parseProperties(propertiesFileName)::apply, constructor);
  }

  public <T> T from(Class<? extends T> tClass, UnaryOperator<String> getProperty) {
    return from(getProperty, getMaxArgsCountConstructor(tClass));
  }

  public <T> T from(UnaryOperator<String> getProperty, Constructor<? extends T> constructor) {
    return CheckedFunction1.<Object[], T>of(constructor::newInstance).unchecked().apply(
        constructor.getParameters().stream()
            .map(parameter -> resolveParameter(getProperty, parameter))
            .toArray());
  }

  public Function1<String, String> parseProperties(String propertiesFileName) {
    val properties = new Properties();
    "%s.properties"
        .formatted(propertiesFileName)
        .withFileInputStreamChecked(properties::load);
    return properties::getProperty;
  }

  /**
   * Возвращает конструктор с максимальным кол-вом параметров.
   *
   * @param tClass
   * @param <T>
   * @return
   */
  @SuppressWarnings("unchecked")
  public <T> Constructor<T> getMaxArgsCountConstructor(Class<? extends T> tClass) {
    Comparator<Constructor<T>> comparator = comparingInt(Constructor::getParameterCount);
    return ((Constructor<T>[]) tClass.getConstructors()).stream()
                                                        .max(comparator)
                                                        .orElseThrow(() -> new PropsBinderException("Нет ни одного конструктора!"));
  }

  @SuppressWarnings("unchecked")
  public <T> T resolveParameter(UnaryOperator<String> getValue, Parameter parameter) {
    return resolveParameter(getValue, parameter.getName(), (Class<? extends T>) parameter.getType());
  }

  @SuppressWarnings("unchecked")
  public <T> T resolveParameter(UnaryOperator<String> getValue, String name, Class<? extends T> type) {
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

  @SuppressWarnings("unchecked")
  private <T extends Enum<T>> T toEnumValue(String s, Class<?> aClass) {
    return Enum.valueOf((Class<T>) aClass, s);
  }

  private <T> T resolveObjectParameter(UnaryOperator<String> getProperty, Class<? extends T> type, String prefix) {
    if (type.isInterface() || Modifier.isAbstract(type.getModifiers()))
      throw new PropsBinderException("Type must not be an interface or abstract class!");
    return from(type, s -> getProperty.apply("%s.%s".formatted(prefix, s)));
  }

  @Target(TYPE)
  @Retention(RUNTIME)
  public @interface InitProperties {
    String value();
  }
}

@StandardException
class PropsBinderException extends RuntimeException {
}
