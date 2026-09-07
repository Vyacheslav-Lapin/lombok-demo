package ru.vlapin.demo.lombokdemo.common;

import static io.vavr.API.*;

import io.vavr.CheckedFunction1;
import io.vavr.CheckedFunction2;
import io.vavr.Tuple2;
import java.lang.annotation.Annotation;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.jspecify.annotations.NonNull;
import org.springframework.core.annotation.AnnotatedElementUtils;

/**
 * Utility class for working with AspectJ JoinPoints and annotations.
 * <p>
 * Provides methods to extract methods, annotations, and perform operations on methods and annotations present in JoinPoints, like those
 * that appear in AOP contexts.
 */
@UtilityClass
@ExtensionMethod(suppressBaseMethods = false, value = {
    AnnotatedElementUtils.class,
    ReflectionUtils.class,
    OptionalUtils.class,
    Optional.class,
    Objects.class,
})

public class AspectUtils {

  /**
   * Get method from {@link JoinPoint} object.
   *
   * @param joinPoint argument of advice-method
   */
  @SneakyThrows
  public Optional<Method> getImplMethod(@NonNull JoinPoint joinPoint) {
    // Extracts method from join point; resolves abstract methods
    return joinPoint.ofNullable()
                    .map(JoinPoint::getSignature)
                    .filterIsInstance(MethodSignature.class)
                    .map(MethodSignature::getMethod)
                    .flatMap(method -> method.isAbstract()
                        ? joinPoint.getTarget().ofNullable()
                                   .map(Object::getClass)
                                   .map(unchecked(targetClass -> resolveMethod(targetClass, method)))
                                   .or(() -> method.of()) // fallback: лучше вернуть декларацию, чем падать с NPE...
                        : method.of());
  }

  public <A extends Annotation> Optional<Tuple2<Method, A>> findAnnotatedMethod(@NonNull JoinPoint joinPoint,
                                                                                @NonNull Class<A> annotationType) {
    return joinPoint.ofNullable()
                    .map(JoinPoint::getSignature)
                    .filterIsInstance(MethodSignature.class)
                    .map(MethodSignature::getMethod)
                    .flatMap(declaredMethod -> {
                      val declaringType = declaredMethod.getDeclaringClass();
                      val targetType = joinPoint.getTarget().getClass();
                      if (declaringType.equals(targetType) && !declaredMethod.isAbstract()) {
                        return findAnnotation(declaredMethod, annotationType)
                                              .map(annotation -> Tuple(declaredMethod, annotation));
                      }

                      return targetType.ofNullable().flatMap(_ -> {
                        try {
                          val resolvedMethod = resolveMethod(targetType, declaredMethod);
                          return findAnnotation(resolvedMethod, annotationType)
                                               .map(annotation -> Tuple(resolvedMethod, annotation));
                        } catch (Exception e) {
                          return Optional.<Tuple2<Method, A>>empty();
                        }});
                    });
  }

  /**
   * Resolves method via public methods then declared methods
   */
  public Method resolveMethod(@NonNull Class<?> targetClass,
                               @NonNull Method declaration) throws NoSuchMethodException {
    val name = declaration.getName();
    val paramTypes = declaration.getParameterTypes();

    // 1) Сначала пробуем public-методы с учетом наследования
    try {
      return targetClass.getMethod(name, paramTypes);
    } catch (NoSuchMethodException _) {
      // 2) Идем вверх по иерархии и ищем declared-метод
      var searchClass = targetClass;
      while (searchClass != null)
        try {
          return searchClass.getDeclaredMethod(name, paramTypes);
        } catch (NoSuchMethodException _) {
          searchClass = searchClass.getSuperclass();
        }

      // 3) Если не нашли — пусть вызывающий решает, что делать
      throw new NoSuchMethodException("%s#%s".formatted(targetClass.getName(), name));
    }
  }

  /**
   * Retrieves the specified annotation from a method or its declaring class.
   *
   * @param pjp             the join point representing the currently executing method
   * @param annotationClass the class of the annotation to be retrieved
   * @param <A>             the type of the annotation to be returned
   * @return the annotation instance if found on the method or its declaring class, otherwise throws an exception
   */
  public <A extends Annotation> A getAnnotation(JoinPoint pjp,
                                                Class<A> annotationClass) {
    val method = getImplMethod(pjp).orElseThrow();
    return method.findMergedAnnotation(annotationClass)
                 .requireNonNullElseGet(() -> method.getDeclaringClass().findMergedAnnotation(annotationClass));
  }

  /**
   * Retrieves a tuple containing a specified annotation and the corresponding method from the provided join point.
   *
   * @param jp              the join point representing the currently executing method
   * @param annotationClass the class of the annotation to be retrieved
   * @param <A>             the type of the annotation to be returned
   * @return a tuple where the first element is the annotation instance (if present) and the second element is the method from which the
   * annotation was retrieved
   * @throws IllegalArgumentException if the method cannot be resolved from the join point
   */
  public <A extends Annotation> Tuple2<A, Method> getAnnotationAndMethod(JoinPoint jp,
                                                                         Class<A> annotationClass) {
    val method = getImplMethod(jp).orElseThrow();
    return Tuple(method.findMergedAnnotation(annotationClass), method);
  }

  /**
   * Extracts an annotation and the corresponding method from the provided join point, applies the given function to them, and returns the
   * result of the function's execution.
   *
   * @param <A>             the type of the annotation
   * @param <R>             the type of the result produced by the specified function
   * @param jp              the join point representing the currently executing method
   * @param annotationClass the class of the annotation to be retrieved
   * @param method          a function to be applied to the extracted annotation and method
   * @return the result of applying the specified function to the annotation and method obtained from the join point
   * @throws IllegalArgumentException if the method cannot be resolved from the join point
   */
  public <A extends Annotation, R> R destruct(JoinPoint jp,
                                              Class<? extends A> annotationClass,
                                              @NonNull CheckedFunction2<? super A, ? super Method, ? extends R> method) {
    return getAnnotationAndMethod(jp, annotationClass)
        .apply(method.unchecked());
  }

  /**
   * Executes a specified function on the resolved method of the provided join point and returns the result.
   *
   * @param <R>    the type of the result produced by the function
   * @param jp     the join point representing the currently executing method
   * @param method a function to be applied to the resolved method
   * @return the result of applying the function to the resolved method
   * @throws IllegalArgumentException if the method cannot be resolved from the join point
   */
  public <R> R destruct(JoinPoint jp,
                        @NonNull CheckedFunction1<? super Method, ? extends R> method) {
    return method.unchecked()
                 .apply(getImplMethod(jp).orElseThrow());
  }

  /**
   * Retrieves a specified annotation from the provided method.
   *
   * @param <A>    the type of the annotation to be retrieved
   * @param method the method from which the annotation is to be retrieved
   * @param annotationClass the class object representing the annotation type
   * @return the annotation instance if found, otherwise throws an exception
   */
  public <A extends Annotation> A getAnnotation(@NonNull Method method, Class<A> annotationClass) {
    return method.findMergedAnnotation(annotationClass)
                 .requireNonNull();
  }

  /**
   * Retrieves an annotation of a specified type from the given executable or its declaring class.
   *
   * @param <A>             the type of the annotation to be retrieved
   * @param executable      the executable (e.g., method or constructor) from which the annotation will be fetched
   * @param annotationClass the class object representing the annotation type to be retrieved
   * @return an {@link Optional} containing the annotation instance if found on the executable or its declaring class,
   *         otherwise an empty {@link Optional}
   */
  public <A extends Annotation> Optional<A> findAnnotation(@NonNull Executable executable,
                                                           @NonNull Class<A> annotationClass) {
    return executable.findMergedAnnotation(annotationClass)
                     .ofNullable()
                     .or(() -> executable.getDeclaringClass()
                                         .findMergedAnnotation(annotationClass)
                                         .ofNullable());
  }
}
