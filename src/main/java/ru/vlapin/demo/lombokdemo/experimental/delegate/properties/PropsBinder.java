package ru.vlapin.demo.lombokdemo.experimental.delegate.properties;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;
import java.util.function.UnaryOperator;

import io.vavr.CheckedFunction0;
import io.vavr.CheckedFunction1;
import io.vavr.Function1;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import ru.vlapin.demo.lombokdemo.experimental.delegate.commons.InputStreamUtils;

import static io.vavr.API.*;

@UtilityClass
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
        Arrays.stream(constructor.getParameters())
            .map(parameter -> resolveParameter(getProperty, parameter))
            .toArray());
  }

  @NotNull
  public Function1<String, String> parseProperties(String propertiesFileName) {
    final var properties = new Properties();
    InputStreamUtils.withFileInputStream(propertiesFileName + ".properties", properties::load);
    return properties::getProperty;
  }

  @SuppressWarnings("unchecked")
  @NotNull
  public <T> Constructor<T> getMaxArgsCountConstructor(@NotNull Class<T> tClass) {
    return (Constructor<T>) Arrays.stream(tClass.getConstructors())
                                .max(Comparator.comparingInt(Constructor::getParameterCount))
                                .orElseThrow(() -> new PropsBinderException("Нету ни одного конструктора!"));
  }

  public Object resolveParameter(@NotNull Function1<String, String> getValue,
                                 @NotNull Parameter parameter) {
    return resolveParameter(getValue, parameter.getName(), parameter.getType());
  }

  public Object resolveParameter(@NotNull Function1<String, String> getValue, String name, Class<?> type) {

    return Match(type).of(
        Case($(t -> t == String.class), () -> getValue.apply(name)),
        //        Case($(Class::isArray), () -> Array.getLength(result) == 0),
        Case($(t -> t == Class.class), CheckedFunction0.<Class<?>>of(() -> Class.forName(getValue.apply(name))).unchecked()),
        Case($(Class::isEnum), () -> {

          val s = getValue.apply(name);



          return null;
        }),
        Case($(t -> t == int.class || t == Integer.class), () -> Integer.parseInt(getValue.apply(name))),
        Case($(t -> t == double.class || t == Double.class), () -> Double.parseDouble(getValue.apply(name))),
        Case($(t -> t == long.class || t == Long.class), () -> Long.parseLong(getValue.apply(name))),
        Case($(t -> t == boolean.class || t == Boolean.class), () -> Boolean.parseBoolean(getValue.apply(name))),
        Case($(t -> t == char.class || t == Boolean.class), () -> getValue.apply(name).charAt(0)),
        Case($(t -> t == short.class || t == Short.class), () -> Short.parseShort(getValue.apply(name))),
        Case($(t -> t == byte.class || t == Byte.class), () -> Byte.parseByte(getValue.apply(name))),

        Case($(), () -> resolveObjectParameter(getValue::apply, type, name))
    );

    //    if (type == boolean.class || type == Boolean.class) return Boolean.parseBoolean(getValue.apply(name));
    //    if (type == char.class || type == Character.class) return getValue.apply(name).charAt(0);
    //    if (type == float.class || type == Float.class) return Float.parseFloat(getValue.apply(name));
    //    if (type == short.class || type == Short.class) return Short.parseShort(getValue.apply(name));
    //    if (type == byte.class || type == Byte.class) return Byte.parseByte(getValue.apply(name));
    //
    //    return resolveObjectParameter(getValue::apply, type, name);
  }

  @NotNull
  static <T> T resolveObjectParameter(UnaryOperator<String> getProperty,
                                      @NotNull Class<T> type,
                                      String prefix) {
    if (type.isInterface() || Modifier.isAbstract(type.getModifiers()))
      throw new PropsBinderException("Type must not be an interface or abstract class!");
    return from(s -> getProperty.apply(String.format("%s.%s", prefix, s)), type);
  }
}
