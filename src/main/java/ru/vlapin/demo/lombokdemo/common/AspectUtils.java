package ru.vlapin.demo.lombokdemo.common;

import io.vavr.CheckedFunction1;
import io.vavr.CheckedFunction2;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;

@UtilityClass
@ExtensionMethod({
    AnnotatedElementUtils.class,
})
public class AspectUtils {

  /**
   * Get method from {@link ProceedingJoinPoint} object.
   *
   * @param pjp
   * @return
   */
  @SneakyThrows
  public Optional<Method> getMethod(@NotNull ProceedingJoinPoint pjp) {
    if (pjp.getSignature() instanceof MethodSignature signature) {
      val method = signature.getMethod();
      return Optional.of(method.getDeclaringClass().isInterface() ?
          pjp.getTarget()
              .getClass()
              .getDeclaredMethod(signature.getName(), method.getParameterTypes())
          : method);
    } else
      return Optional.empty();
  }

  public <A extends Annotation> A getAnnotation(ProceedingJoinPoint pjp,
                                                Class<A> annotationClass) {
    return getMethod(pjp)
               .flatMap(method -> Optional.ofNullable(
                   Optional.ofNullable(method.findMergedAnnotation(annotationClass))
                       .orElseGet(() -> method.getDeclaringClass().findMergedAnnotation(annotationClass))))
               .orElseThrow();
  }

  public <A extends Annotation> Tuple2<A, Method> getAnnotationAndMethod(ProceedingJoinPoint pjp,
                                                                         Class<A> annotationClass) {
    return getMethod(pjp)
               .map(method -> Tuple.of(method.findMergedAnnotation(annotationClass), method))
               .orElseThrow();
  }

  public <A extends Annotation, R> R destruct(ProceedingJoinPoint pjp,
                                              Class<A> annotationClass,
                                              @NotNull CheckedFunction2<A, Method, R> method) {
    return getAnnotationAndMethod(pjp, annotationClass)
               .apply(method.unchecked());
  }

  public <R> R destruct(ProceedingJoinPoint pjp,
                        @NotNull CheckedFunction1<Method, R> method) {
    return method.unchecked()
               .apply(getMethod(pjp).orElseThrow());
  }

  public <A extends Annotation> A getAnnotation(@NotNull Method method,
                                                Class<A> aClass) {
    return Optional.ofNullable(method.findMergedAnnotation(aClass))
               .orElseThrow();
  }
}
