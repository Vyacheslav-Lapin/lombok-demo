package ru.vlapin.demo.lombokdemo.common;

import io.vavr.CheckedFunction1;
import io.vavr.CheckedFunction2;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("unused")

@UtilityClass
@ExtensionMethod({
    AnnotatedElementUtils.class,
    Objects.class,
})
public class AspectUtils {

  /**
   * Get method from {@link ProceedingJoinPoint} object.
   *
   * @param pjp argument of advice-method
   */
  @SneakyThrows
  public Optional<Method> getMethod(JoinPoint pjp) {
    if (pjp.getSignature() instanceof MethodSignature methodSignature) {
      val method = methodSignature.getMethod();
      return Optional.of(method.getDeclaringClass().isInterface() ?
          pjp.getTarget()
              .getClass()
              .getDeclaredMethod(methodSignature.getName(), method.getParameterTypes())
          : method);
    } else
      return Optional.empty();
  }

  public <A extends Annotation> A getAnnotation(JoinPoint pjp,
                                                Class<A> annotationClass) {
    val method = getMethod(pjp).orElseThrow();
    return method.findMergedAnnotation(annotationClass)
        .requireNonNullElseGet(() -> method.getDeclaringClass().findMergedAnnotation(annotationClass));
  }

  public <A extends Annotation> Tuple2<A, Method> getAnnotationAndMethod(JoinPoint jp,
                                                                         Class<A> annotationClass) {
    val method = getMethod(jp).orElseThrow();
    return Tuple.of(method.findMergedAnnotation(annotationClass), method);
  }

  public <A extends Annotation, R> R destruct(JoinPoint jp,
                                              Class<? extends A> annotationClass,
                                              CheckedFunction2<? super A, ? super Method, ? extends R> method) {
    return getAnnotationAndMethod(jp, annotationClass)
        .apply(method.unchecked());
  }

  public <R> R destruct(JoinPoint jp,
                        CheckedFunction1<? super Method, ? extends R> method) {
    return method.unchecked()
               .apply(getMethod(jp).orElseThrow());
  }

  public <A extends Annotation> A getAnnotation(Method method, Class<A> aClass) {
    return method.findMergedAnnotation(aClass)
        .requireNonNull();
  }
}
