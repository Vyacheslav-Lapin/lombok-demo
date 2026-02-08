package ru.vlapin.demo.lombokdemo.common;

import static io.vavr.API.*;
import static java.lang.reflect.Modifier.*;

import io.vavr.API;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedFunction1;
import io.vavr.CheckedFunction3;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.collection.Stream;
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
import java.util.Spliterators;
import java.util.function.IntPredicate;
import java.util.stream.StreamSupport;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.springframework.core.annotation.AnnotatedElementUtils;

/**
 * Utility class that provides reflection-based utilities for working with classes, constructors, methods,
 * and other reflection-related operations.
 */
@UtilityClass
@SuppressWarnings({"unused", "java:S125"})
@ExtensionMethod(suppressBaseMethods = false,
                 value = {
                     Proxy.class,
                     Arrays.class,
                     ArrayUtils.class,
                     Spliterators.class,
                     StreamSupport.class,
                     Function2Utils.class,
                     AnnotatedElementUtils.class,
                     CheckedFunction1Utils.class,
                 })
public class ReflectionUtils {

  /**
   * A predicate that determines if a given integer represents a package-private access modifier.
   * This is achieved by ensuring the integer does not correspond to {@code private}, {@code public}, or {@code protected} access modifiers.
   */
  public final IntPredicate isPackagePrivate = x -> !(isPrivate(x) || isPublic(x) || isProtected(x));
//      ((IntPredicate) Modifier::isPrivate)
//          .or(Modifier::isProtected)
//          .or(Modifier::isPublic)
//          .negate();

  private final Function2<String, String, Stream<Class<?>>> GET_CLASS_FROM_FILE = (packageName, fileName) -> {
    val className = fileName.substring(0, fileName.length() - 6); // ".class".length() is 6
    val fullClassName = "%s.%s".formatted(packageName, className);
    val aClass = API.<String, Class<?>>unchecked(Class::forName).apply(fullClassName);
    return Stream(aClass);
  };

  /**
   * Determines the runtime class of the given object.
   *
   * @param <T>   the type of the input object
   * @param $this the instance whose class is to be determined
   * @return the class of the given object
   */
  @SuppressWarnings("unchecked")
  public <T> Class<T> clazz(T $this) {
    return (Class<T>) $this.getClass();
  }

  /**
   * Returns a function that constructs a new instance of a class with a no-argument constructor.
   * <p>
   * This method leverages reflection to find the no-argument constructor of the given class and
   * subsequently invoke it to create a new instance.
   *
   * @param <T> the type of the object to be created
   * @return a function that takes a {@code Class<? extends T>} as input and produces a new instance of {@code T}.
   *         The function throws an exception if the class does not have a no-argument constructor
   *         or if the instantiation fails for any other reason.
   */
  public <T> CheckedFunction1<? super Class<? extends T>, T> noArgsConstructor() {
    return CheckedFunction1.<Class<? extends T>, Constructor<? extends T>>of(Class::getDeclaredConstructor)
                           .andThen(Constructor::newInstance);
  }

  /**
   * Creates and returns a function that produces a new instance of the specified class {@code T}
   * using its no-argument constructor.
   *
   * @param <T>    the type of the object to be created
   * @param tClass the class of type {@code T} for which a no-argument constructor function is to be created
   * @return a function that, when called, produces a new instance of the specified class {@code T}.
   *         Throws an exception if the no-argument constructor is not available or the instance creation fails.
   */
  @SuppressWarnings({"java:S125", "java:S1135"})
  public <T> CheckedFunction0<T> noArgsConstructor(Class<T> tClass) {
//  public <T> CheckedFunction0<T> noArgsConstructor(Class<? extends T> tClass) { //todo 07.05.2023: create bug report for that false positive error

//    return ReflectionUtils.<T>noArgsConstructor().supply(tClass);
    return noArgsConstructor().supply(tClass);
  }

  /**
   * Creates and returns a new instance of the specified class using its no-argument constructor.
   *
   * @param <T>    the type of the object to be created
   * @param tClass the class of type {@code T} for which a new instance is to be created
   * @return a new instance of the specified class {@code T}
   * @throws RuntimeException if the no-argument constructor is not available or the instantiation fails
   */
  public <T> T newObject(Class<? extends T> tClass) {
    return noArgsConstructor(tClass)
        .unchecked()
        .get();
  }

  /**
   * Retrieves a stream of methods from the specified class that are non-synthetic,
   * non-static, and annotated with the given annotation.
   *
   * @param <T>              the type parameter associated with the {@code testExampleClass}
   * @param testExampleClass the class whose methods are to be examined
   * @param annotationClass  the annotation class used to filter the methods
   * @return a stream of methods that are non-synthetic, non-static, and annotated
   *         with the specified annotation
   */
  public <T> Stream<Method> annotatedMethods(Class<? extends T> testExampleClass,
                                             Class<? extends Annotation> annotationClass) {
    return Stream(testExampleClass.getDeclaredMethods())
                           .filter(method -> !method.isSynthetic())
                           .filter(method -> !isStatic(method.getModifiers()))
                           //.filter(method -> method.isAnnotationPresent(annotationClass))
                           .filter(method -> method.isAnnotated(annotationClass));
  }

  /**
   * Converts the provided fully qualified class name into a {@link Class} object.
   * This method uses reflection to load the class and casts it to the specified generic type.
   *
   * @param <T>       the type of the class object to be returned
   * @param className the fully qualified name of the class to be converted
   * @return the {@link Class} object of the specified type corresponding to the input class name
   * @throws RuntimeException if the class cannot be found, or if any other reflection-related exception occurs
   */
  @SuppressWarnings("unchecked")
  public <T> Class<T> toClass(String className) {
    return unchecked((String name) -> (Class<T>) Class.forName(name))
              .apply(className);
  }

  /**
   * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
   *
   * @param packageName The base package
   * @return The classes
   */
  public Stream<Class<?>> getClasses(String packageName) {
    val findClasses = Function(ReflectionUtils::findClasses)
                               .apply(packageName);

    return unchecked(ClassLoader::getResources)
        .reversed()
        .apply(packageName.replace('.', '/'))
        .andThen(Enumeration::asIterator)
        .andThen(urlIterator -> Stream.ofAll(() -> urlIterator))
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
    val lookForClasses = Function(ReflectionUtils::lookForClasses)
        .apply(packageName);
    return Option(directory)
                   .filter(File::exists)
                   .map(File::listFiles)
                   .toStream()
                   .flatMap(API::Array)
                   .flatMap(lookForClasses);
  }

  /**
   * Searches for and retrieves a stream of classes located within the specified package
   * and file. This method determines whether the file is a directory or a regular file
   * and applies the appropriate logic to extract the classes.
   *
   * @param packageName The name of the package to which the classes belong.
   * @param file        The file or directory to search for classes.
   * @return A stream of classes found in the specified package and file.
   */
  private Stream<Class<?>> lookForClasses(String packageName, File file) {
    val fileName = file.getName();
    return (file.isDirectory() && fileName.contains(".") ?
        GET_CLASS_FROM_DIR.apply(file) : GET_CLASS_FROM_FILE)
        .apply(packageName, fileName);
  }

  /**
   * Retrieves a stream of parameter names for the given executable. If the executable is a constructor
   * annotated with {@code @ConstructorProperties} and its parameters have default generated names (e.g., starting with "arg"),
   * the method will extract parameter names from the annotation. Otherwise, it retrieves the parameter names using reflection.
   *
   * @param executable the executable (constructor or method) whose parameter names are to be retrieved
   * @return a stream of parameter names for the provided executable
   */
  public Stream<String> paramNames(Executable executable) {
    val nativeParams = executable.getParameters();

    // Extracts constructor parameter names from annotation if present
    if (executable instanceof Constructor<?>
        && executable.isAnnotationPresent(ConstructorProperties.class)
        && nativeParams.length >= 1
        && nativeParams[0].getName().startsWith("arg")) {
      val argumentNames = executable.getAnnotation(ConstructorProperties.class).value();
      if (argumentNames.length == nativeParams.length)
        return Stream(argumentNames);
    }

    return Stream(nativeParams)
        .map(Parameter::getName);
  }

  private final Function3<File, String, String, Stream<Class<?>>> GET_CLASS_FROM_DIR =
      Function((file, packageName, fileName) ->
          findClasses("%s.%s".formatted(packageName, fileName), file));

  /**
   * Creates a new dynamic proxy instance that implements the specified interface(s) and delegates method calls
   * to the provided invocation handler.
   *
   * @param <T>                the type of the proxy class
   * @param mainInterface      the primary interface that the proxy instance should implement
   * @param invocationHandler  the handler to invoke when a method is called on the proxy instance
   * @param additionalInterfaces any additional interfaces that the proxy instance should implement
   * @return a proxy instance that implements the specified interface(s) and uses the provided invocation handler
   */
  @SuppressWarnings("unchecked")
  public <T> T newProxyInstance(Class<? extends T> mainInterface,
                                CheckedFunction3<? super T, Method, Object[], Object> invocationHandler,
                                Class<?>... additionalInterfaces) {
    return (T) Proxy.newProxyInstance(
        ReflectionUtils.class.getClassLoader(),
        additionalInterfaces.add(mainInterface),
        (proxy, method, args) -> invocationHandler.apply((T) proxy, method, args));
  }

  /**
   * Retrieves a declared class from the specified enclosing class by its simple name.
   * This method searches for a nested class within the provided class and returns the corresponding
   * {@code Class} object if a match is found. The search is performed using the class's declared
   * classes, and an exception is thrown if the target class cannot be found.
   *
   * @param <T>       the type of the declared class to be returned
   * @param $this     the enclosing class that contains the nested declared class
   * @param className the simple name of the declared class to be retrieved
   * @return the {@code Class} object of the nested declared class matching the specified name
   * @throws IllegalArgumentException if the specified declared class cannot be found
   */
  @SuppressWarnings("unchecked")
  public <T> Class<T> declaredClass(Class<?> $this, String className) {
    // Filters declared classes by name; returns first match
    return (Class<T>) Array($this.getDeclaredClasses())
                            .filter(clazz -> clazz.getName().equals("%s$%s".formatted($this.getName(), className)))
                            .headOption()
                            .getOrElseThrow(IllegalArgumentException::new);
  }
}
