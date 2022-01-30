package ru.vlapin.demo.lombokdemo.common;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.function.BiConsumer;

import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
import static ru.vlapin.demo.lombokdemo.common.Loggable.LogLevel.*;

/**
 * Aspect annotation.
 *
 * @see LoggableAspect#aroundAdvice(ProceedingJoinPoint)
 */
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface Loggable {
  enum LogLevel {
    ERROR, WARN, INFO, DEBUG, TRACE;
  }

  LogLevel value() default DEBUG;
}

@Slf4j
@Aspect
@ExtensionMethod(AspectUtils.class)
final class LoggableAspect {

  @SneakyThrows
  @Around("@annotation(Loggable) || within(@Loggable *)")
  Object aroundAdvice(ProceedingJoinPoint pjp) {
    val methodName = pjp.getSignature().getName();
    val args = pjp.getArgs();
    val logMethod = getLogMethod(pjp);

    logMethod.accept("Метод {} был вызван с аргументами {}", new Object[]{methodName, args});

    val result = pjp.proceed();

    logMethod.accept("Method {} вернул {} для аргументов {}", new Object[]{methodName, result, args});

    return result;
  }

  private BiConsumer<String, Object[]> getLogMethod(ProceedingJoinPoint pjp) {
    return switch (pjp.getAnnotation(Loggable.class).value()) {
      case INFO -> log::info;
      case DEBUG -> log::debug;
      case WARN -> log::warn;
      case ERROR -> log::error;
      case TRACE -> log::trace;
    };
  }
}
