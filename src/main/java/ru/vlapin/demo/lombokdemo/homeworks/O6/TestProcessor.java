package ru.vlapin.demo.lombokdemo.homeworks.O6;

import static java.util.stream.Collectors.*;
import static lombok.AccessLevel.*;

import io.vavr.CheckedConsumer;
import io.vavr.Function1;
import io.vavr.control.Try;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.With;
import lombok.experimental.Accessors;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;
import ru.vlapin.demo.lombokdemo.common.CheckedConsumerUtils;
import ru.vlapin.demo.lombokdemo.common.ReflectionUtils;
import ru.vlapin.demo.lombokdemo.common.StreamUtils;

@UtilityClass
@ExtensionMethod({
        Arrays.class,
        StreamUtils.class,
        ReflectionUtils.class,
        CheckedConsumerUtils.class,
})
public class TestProcessor {

  private static <T> Function<? super Class<? extends Annotation>, Stream<? extends TestMethod<T>>> annotatedMethods(Class<? extends T> testExampleClass) {
    return annotationClass -> testExampleClass.annotatedMethods(annotationClass)
            .peek(method -> method.setAccessible(true))
            .map(TestMethod::new);
  }

  private static <T> Function<? super TestMethod<? super T>, ? extends Try<Void>> toTry(Class<? extends T> testExampleClass) {
    return Function1.<TestMethod<? super T>, CheckedConsumer<? super T>>of(TestMethod::consumer)
            .andThen(checkedConsumer -> checkedConsumer.compose(Function1.<Class<? extends T>, T>of(ReflectionUtils::newObject)))
//            .andThen(checkedConsumer -> checkedConsumer.supply(testExampleClass)) //todo 07.05.2023: create bug report for that false positive error
            .andThen(checkedConsumer -> CheckedConsumerUtils.supply(checkedConsumer, testExampleClass))
            .andThen(Try::run);

//    return testMethod -> Try.run(() ->
//            testMethod.consumer().accept(
//                    testExampleClass.newObject()));
  }

  public <T> Map<Method, ? extends Try<Void>> runTests(Class<T> testExampleClass) {
    val getAnnotatedMethods = annotatedMethods(testExampleClass);
    return getAnnotatedMethods.apply(Test.class)
            .map(test -> test.withConsumer(
                    getAnnotatedMethods.apply(Before.class)
                            .map(TestMethod::consumer)
                            .reverse()
                            .reduce(test.consumer(), CheckedConsumerUtils::atFirst)))
            .map(test -> test.withConsumer(
                    getAnnotatedMethods.apply(After.class)
                            .map(TestMethod::consumer)
                            .reduce(test.consumer(), CheckedConsumer::andThen)))
            .collect(toUnmodifiableMap(TestMethod::method, toTry(testExampleClass)));
  }

  @Value
  @Accessors(fluent = true)
  @RequiredArgsConstructor(access = PRIVATE)
  private static class TestMethod<T> {

    Method method;

    @With
    CheckedConsumer<T> consumer;

    public TestMethod(Method method) {
      this(method, method::invoke);
    }
  }
}
