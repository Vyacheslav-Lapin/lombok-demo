package ru.vlapin.demo.lombokdemo.common;

import io.vavr.*;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Spliterator.*;
import static java.util.Spliterators.*;
import static java.util.stream.StreamSupport.*;

@UtilityClass
@SuppressWarnings("unused")
@ExtensionMethod({
    Function2Utils.class,
    CheckedFunction1Utils.class,
})
public class ReflectionUtils {

  private final Function2<String, String, Stream<Class<?>>> GET_CLASS_FROM_FILE =
      Function2.<String, String, String>of("%s.%s"::formatted)
          .compose2((String name) -> name.substring(0, name.length() - 6))
          .andThen(CheckedFunction1.<String, Class<?>>of(Class::forName)
              .unchecked())
          .andThen(Stream::of);

  public <T> CheckedFunction1<Class<T>, T> noArgsConstructor() {
    return CheckedFunction1.<Class<T>, Constructor<T>>of(Class::getConstructor)
        .andThen(Constructor::newInstance);
  }

  public <T> CheckedFunction0<T> noArgsConstructor(Class<T> tClass) {
    return noArgsConstructor().supply(tClass);
  }

  public <T> T newObject(Class<? extends T> tClass) {
    return noArgsConstructor(tClass)
        .unchecked()
        .get();
  }

  public <T> Class<T> toClass(String className) {
    //noinspection unchecked
    return (Class<T>) CheckedFunction1.<String, Class<?>>of(Class::forName)
        .unchecked()
        .apply(className);
  }

  private final Function3<File, String, String, Stream<Class<?>>> GET_CLASS_FROM_DIR =
      Function3.of((file, packageName, fileName) ->
          findClasses(file, "%s.%s".formatted(packageName, fileName)));

  /**
   * Scans all classes accessible from the context class loader which belong to the given package
   * and subpackages.
   *
   * @param packageName The base package
   * @return The classes
   */
  public Stream<Class<?>> getClasses(@NotNull String packageName) {
    return CheckedFunction2.of(ClassLoader::getResources).unchecked()
        .reversed()
        .apply(packageName.replace('.', '/'))
        .andThen(Enumeration::asIterator)
        .andThen(urlIterator -> spliteratorUnknownSize(urlIterator, ORDERED))
        .andThen(urlSpliterator -> stream(urlSpliterator, false))
        .compose(Thread::getContextClassLoader)
        .apply(Thread.currentThread())
        .map(URL::getFile)
        .map(File::new)
        .flatMap(directory -> findClasses(directory, packageName));
  }

  /**
   * Recursive method used to find all classes in a given directory and subdirs.
   *
   * @param directory   The base directory
   * @param packageName The package name for classes found inside the base directory
   * @return The classes
   */
  @SneakyThrows
  private Stream<Class<?>> findClasses(File directory, String packageName) {

    val lookForClasses =
        Function2.of(ReflectionUtils::lookForClasses)
            .apply(packageName);

    return Optional.of(directory)
        .filter(File::exists)
        .map(File::listFiles)
        .stream()
        .flatMap(Arrays::stream)
        .flatMap(lookForClasses);
  }

  private Stream<Class<?>> lookForClasses(String packageName, @NotNull File file) {
    val fileName = file.getName();
    return (file.isDirectory() && fileName.contains(".") ?
                GET_CLASS_FROM_DIR.apply(file) : GET_CLASS_FROM_FILE)
        .apply(packageName, fileName);
  }
}
