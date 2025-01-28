package ru.vlapin.demo.lombokdemo.common;

import static java.lang.reflect.Modifier.*;
import static java.util.Spliterator.*;

import io.vavr.CheckedFunction0;
import io.vavr.CheckedFunction1;
import io.vavr.CheckedFunction2;
import io.vavr.CheckedFunction3;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.collection.Array;
import java.beans.ConstructorProperties;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Optional;
import java.util.Spliterators;
import java.util.function.IntPredicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.springframework.core.annotation.AnnotatedElementUtils;

@UtilityClass
@SuppressWarnings({"unused", "java:S125"})
@ExtensionMethod(suppressBaseMethods = false,
                 value = {
                     Proxy.class,
                     Arrays.class,
                     Spliterators.class,
                     StreamSupport.class,
                     Function2Utils.class,
                     AnnotatedElementUtils.class,
                     CheckedFunction1Utils.class,
                 })
public class ReflectionUtils {

  public final IntPredicate isPackagePrivate = x -> !(isPrivate(x) || isPublic(x) || isProtected(x));
//      ((IntPredicate) Modifier::isPrivate)
//          .or(Modifier::isProtected)
//          .or(Modifier::isPublic)
//          .negate();

  private final Function2<String, String, Stream<Class<?>>> GET_CLASS_FROM_FILE =
      Function2.<String, String, String>of("%s.%s"::formatted)
               .compose2((String name) -> name.substring(0, name.length() - 6))
               .andThen(CheckedFunction1.<String, Class<?>>of(Class::forName)
                                        .unchecked())
               .andThen(Stream::of);

  @SuppressWarnings("unchecked")
  public <T> Class<T> clazz(T self) {
    return (Class<T>) self.getClass();
  }

  public <T> CheckedFunction1<? super Class<? extends T>, T> noArgsConstructor() {
    return CheckedFunction1.<Class<? extends T>, Constructor<? extends T>>of(Class::getDeclaredConstructor)
                           .andThen(Constructor::newInstance);
  }

  @SuppressWarnings({"java:S125", "java:S1135"})
  public <T> CheckedFunction0<T> noArgsConstructor(Class<T> tClass) {
//  public <T> CheckedFunction0<T> noArgsConstructor(Class<? extends T> tClass) { //todo 07.05.2023: create bug report for that false positive error

//    return ReflectionUtils.<T>noArgsConstructor().supply(tClass);
    return noArgsConstructor().supply(tClass);
  }

  public <T> T newObject(Class<? extends T> tClass) {
    return noArgsConstructor(tClass)
        .unchecked()
        .get();
  }

  public <T> Stream<Method> annotatedMethods(Class<? extends T> testExampleClass,
                                             Class<? extends Annotation> annotationClass) {
    return testExampleClass.getDeclaredMethods().stream()
                           .filter(method -> !method.isSynthetic())
                           .filter(method -> !isStatic(method.getModifiers()))
                           //.filter(method -> method.isAnnotationPresent(annotationClass))
                           .filter(method -> method.isAnnotated(annotationClass));
  }

  @SuppressWarnings("unchecked")
  public <T> Class<T> toClass(String className) {
    return CheckedFunction1.of((String name) -> (Class<T>) Class.forName(name))
                           .unchecked()
                           .apply(className);
  }

  /**
   * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
   *
   * @param packageName The base package
   * @return The classes
   */
  public Stream<Class<?>> getClasses(String packageName) {
    val findClasses = Function2.of(ReflectionUtils::findClasses)
                               .apply(packageName);

    return CheckedFunction2.of(ClassLoader::getResources).unchecked()
                           .reversed()
                           .apply(packageName.replace('.', '/'))
                           .andThen(Enumeration::asIterator)
                           .andThen(urlIterator -> urlIterator.spliteratorUnknownSize(ORDERED))
                           .andThen(urlSpliterator -> urlSpliterator.stream(false))
                           .compose(Thread::getContextClassLoader)
                           .apply(Thread.currentThread())
                           .map(URL::getFile)
                           .map(File::new)
                           .flatMap(findClasses);
  }

  /**
   * Recursive method used to find all classes in a given directory and subdirs.
   *
   * @param packageName The package name for classes found inside the base directory
   * @param directory   The base directory
   * @return The classes
   */
  @SneakyThrows
  private Stream<Class<?>> findClasses(String packageName, File directory) {

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

  private Stream<Class<?>> lookForClasses(String packageName, File file) {
    val fileName = file.getName();
    return (file.isDirectory() && fileName.contains(".") ?
        GET_CLASS_FROM_DIR.apply(file) : GET_CLASS_FROM_FILE)
        .apply(packageName, fileName);
  }

  public Stream<String> paramNames(Executable executable) {
    val nativeParams = executable.getParameters();

    if (executable instanceof Constructor<?>
        && executable.isAnnotationPresent(ConstructorProperties.class)
        && nativeParams.length >= 1
        && nativeParams[0].getName().startsWith("arg")) {
      val argumentNames = executable.getAnnotation(ConstructorProperties.class)
                                    .value();
      if (argumentNames.length == nativeParams.length)
        return argumentNames.stream();
    }

    return nativeParams.stream().map(Parameter::getName);
  }

  private final Function3<File, String, String, Stream<Class<?>>> GET_CLASS_FROM_DIR =
      Function3.of((file, packageName, fileName) ->
          findClasses("%s.%s".formatted(packageName, fileName), file));

  @SuppressWarnings("unchecked")
  public <T> T newProxyInstance(Class<? extends T> mainInterface,
                                CheckedFunction3<? super T, Method, Object[], Object> invocationHandler,
                                Class<?>... additionalInterfaces) {
    return (T) ReflectionUtils.class
        .getClassLoader()
        .newProxyInstance(
            Array.of(additionalInterfaces).append(mainInterface).toJavaArray(Class<?>[]::new),
            (proxy, method, args) -> invocationHandler.apply((T) proxy, method, args));
  }

  @SuppressWarnings("unchecked")
  public <T> Class<T> declaredClass(Class<?> self, String className) {
    return (Class<T>) Arrays.stream(self.getDeclaredClasses())
                            .filter(clazz -> clazz.getName().equals("%s$%s".formatted(self.getName(), className)))
                            .findFirst()
                            .orElseThrow();
  }
}
