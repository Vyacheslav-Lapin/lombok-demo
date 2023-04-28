package ru.vlapin.demo.lombokdemo.homeworks.O6;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.With;
import lombok.experimental.Accessors;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;
import ru.vlapin.demo.lombokdemo.common.CheckedConsumerUtils;
import ru.vlapin.demo.lombokdemo.common.StreamUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.reflect.Modifier.isStatic;
import static lombok.AccessLevel.PRIVATE;

@UtilityClass
@ExtensionMethod({
        Arrays.class,
        StreamUtils.class,
})
public class TestProcessor {

  private static <T> Function<Class<? extends Annotation>, Stream<TestMethod<T>>> annotatedMethods(Class<? extends T> testExampleClass) {
    return annotationClass -> testExampleClass.getDeclaredMethods().stream()
            .filter(method -> !method.isSynthetic())
            .filter(method -> !isStatic(method.getModifiers()))
            .filter(method -> method.isAnnotationPresent(annotationClass))
            .peek(method -> method.setAccessible(true))
            .map(TestMethod::new);
  }

  private static <T> Supplier<? extends T> getNewInstance(Class<? extends T> testExampleClass) {
    return CheckedFunction0.of(testExampleClass::getConstructor)
            .andThen(Constructor::newInstance)
            .unchecked();
  }

  private static <T> Function<TestMethod<? super T>, Try<Void>> toTry(Class<? extends T> testExampleClass) {
    return testMethod -> Try.run(() ->
            testMethod.consumer().accept(
                    getNewInstance(testExampleClass)
                            .get()));
  }

  public <T> Map<Method, Try<Void>> runTests(Class<T> testExampleClass) {
    val toTry = toTry(testExampleClass);
    val getAnnotatedMethods = annotatedMethods(testExampleClass);
    return getAnnotatedMethods.apply(Test.class)
            .map(test -> test.withConsumer(
                    getAnnotatedMethods.apply(Before.class)
                            .map(TestMethod::consumer)
                            .reverse()
                            .reduce(test.consumer(), CheckedConsumerUtils::compose)))
            .map(test -> test.withConsumer(getAnnotatedMethods.apply(After.class)
                    .map(TestMethod::consumer)
                    .reduce(test.consumer(), CheckedConsumer::andThen)))
            .collect(Collectors.toUnmodifiableMap(TestMethod::method, toTry));
  }

  @Value
  @Accessors(fluent = true)
  @RequiredArgsConstructor(access = PRIVATE)
  private static class TestMethod<T> {

    Method method;

    @With
    CheckedConsumer<T> consumer;

    public TestMethod(Method method) {
      this(method, CheckedConsumer.of(method::invoke));
    }
  }
}
