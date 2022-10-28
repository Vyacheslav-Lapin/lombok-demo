package ru.vlapin.demo.lombokdemo.experimental.delegate.properties;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;
import java.util.function.UnaryOperator;

import io.vavr.CheckedFunction1;
import io.vavr.Function1;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import ru.vlapin.demo.lombokdemo.experimental.delegate.commons.InputStreamUtils;

@UtilityClass
@ExtensionMethod({
    Arrays.class,
    InputStreamUtils.class,
})
public class PropsBinder {

  @NotNull
  public <T> T from(Class<T> tClass) {
    return from(tClass.getSimpleName(), tClass);
  }

  @NotNull
  public <T> T from(String propertiesFileName, Class<T> tClass) {
    return from(
        parseProperties(propertiesFileName),
        getMaxArgsCountConstructor(tClass));
  }

  @NotNull
  public <T> T from(@NotNull String propertiesFileName,
                    @NotNull Constructor<T> constructor) {
    return from(parseProperties(propertiesFileName), constructor);
  }

  @NotNull
  @SneakyThrows
  public <T> T from(Function1<String, String> getProperty, Class<T> tClass) {
    return from(getProperty, getMaxArgsCountConstructor(tClass));
  }

  @NotNull
  @SneakyThrows
  public <T> T from(@NotNull Function1<String, String> getProperty,
                    @NotNull Constructor<T> constructor) {
    return CheckedFunction1.<Object[], T>of(constructor::newInstance).unchecked().apply(
        constructor.getParameters().stream()
            .map(parameter -> resolveParameter(getProperty, parameter))
            .toArray());
  }

  @NotNull
  public Function1<String, String> parseProperties(String propertiesFileName) {
    val properties = new Properties();
    "%s.properties"
        .formatted(propertiesFileName)
        .withFileInputStream(properties::load);
    return properties::getProperty;
  }

  @NotNull
  @SuppressWarnings("unchecked")
  public <T> Constructor<T> getMaxArgsCountConstructor(@NotNull Class<T> tClass) {
    return (Constructor<T>) Arrays.stream(tClass.getConstructors())
                                .max(Comparator.comparingInt(Constructor::getParameterCount))
                                .orElseThrow(() -> new PropsBinderException("Нет ни одного конструктора!"));
  }

  public Object resolveParameter(@NotNull Function1<String, String> getValue,
                                 @NotNull Parameter parameter) {
    return resolveParameter(getValue, parameter.getName(), parameter.getType());
  }

  public <T> T resolveParameter(@NotNull Function1<String, String> getValue,
                                String name,
                                Class<T> type) {
    //noinspection unchecked
    return (T) switch (type) {
      case Class<T> t when t == String.class -> getValue.apply(name);
      case Class<T> t when t.isEnum() -> toEnumValue(getValue.apply(name), t);
      case Class<T> t when t == int.class || t == Integer.class -> Integer.valueOf(getValue.apply(name));
      case Class<T> t when t == boolean.class || t == Boolean.class -> Boolean.valueOf(getValue.apply(name));
      case Class<T> t when t == double.class || t == Double.class -> Double.valueOf(getValue.apply(name));
      case Class<T> t when t == long.class || t == Long.class -> Long.valueOf(getValue.apply(name));
      case Class<T> t when t == char.class || t == Character.class -> getValue.apply(name).charAt(0);
      case Class<T> t when t == float.class || t == Float.class -> Float.valueOf(getValue.apply(name));
      case Class<T> t when t == short.class || t == Short.class -> Short.valueOf(getValue.apply(name));
      case Class<T> t when t == byte.class || t == Byte.class -> Byte.valueOf(getValue.apply(name));
      default -> resolveObjectParameter(getValue::apply, type, name);
    };
  }

  private <T extends Enum<T>> T toEnumValue(String s, Class<?> aClass) {
    //noinspection unchecked
    return Enum.valueOf((Class<T>) aClass, s);
  }

  @NotNull
  private <T> T resolveObjectParameter(UnaryOperator<String> getProperty,
                                       @NotNull Class<T> type,
                                       String prefix) {

    if (type.isInterface() || Modifier.isAbstract(type.getModifiers()))
      throw new PropsBinderException("Type must not be an interface or abstract class!");
    return from(s -> getProperty.apply(String.format("%s.%s", prefix, s)), type);
  }
}
