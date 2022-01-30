package ru.vlapin.demo.lombokdemo.common;

import io.vavr.CheckedFunction1;
import io.vavr.CheckedFunction2;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

@UtilityClass
public class AspectUtils {

  @SneakyThrows
  public Optional<Method> getMethod(ProceedingJoinPoint pjp) {
    if (pjp.getSignature() instanceof MethodSignature signature) {
      val method = signature.getMethod();
      return Optional.of(method.getDeclaringClass().isInterface() ?
                             pjp.getTarget().getClass().getDeclaredMethod(signature.getName(), method.getParameterTypes()) : method);
    } else
      return Optional.empty();
  }

  public <A extends Annotation> A getAnnotation(ProceedingJoinPoint pjp, Class<A> annotationClass) {
    return getMethod(pjp)
        .flatMap(method -> Optional.ofNullable(
            Optional.ofNullable(method.getAnnotation(annotationClass))
                .orElseGet(() -> method.getDeclaringClass().getAnnotation(annotationClass))))
        .orElseThrow();
  }

  public <A extends Annotation> Tuple2<A, Method> getAnnotationAndMethod(ProceedingJoinPoint pjp, Class<A> annotationClass) {
    return getMethod(pjp)
        .map(method -> Tuple.of(method.getAnnotation(annotationClass), method))
        .orElseThrow();
  }

  public <A extends Annotation, R> R destruct(ProceedingJoinPoint pjp,
                                              Class<A> annotationClass,
                                              CheckedFunction2<A, Method, R> method) {
    return getAnnotationAndMethod(pjp, annotationClass)
        .apply(method.unchecked());
  }

  public <R> R destruct(ProceedingJoinPoint pjp, CheckedFunction1<Method, R> method) {
    return method.unchecked().apply(getMethod(pjp).orElseThrow());
  }

  public <A extends Annotation> A getAnnotation(Method method, Class<A> aClass) {
    return Optional.ofNullable(method.getAnnotation(aClass))
        .orElseThrow();
  }
}
