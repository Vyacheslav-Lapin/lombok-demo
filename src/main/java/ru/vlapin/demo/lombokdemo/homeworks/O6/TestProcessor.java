package ru.vlapin.demo.lombokdemo.homeworks.O6;

import io.vavr.CheckedConsumer;
import io.vavr.control.Try;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.With;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;
import ru.vlapin.demo.lombokdemo.common.CheckedConsumerUtils;
import ru.vlapin.demo.lombokdemo.common.ReflectionUtils;
import ru.vlapin.demo.lombokdemo.common.StreamUtils;

@UtilityClass
@ExtensionMethod(suppressBaseMethods = false,
                 value = {
                     Arrays.class,
                     StreamUtils.class,
                     ReflectionUtils.class,
                     CheckedConsumerUtils.class,
                 })
public class TestProcessor {

  private <T> Function<Class<? extends Annotation>, Stream<TestMethod<T>>> testMethods(Class<? extends T> testClass) {
    return annotation -> testClass.annotatedMethods(annotation).toJavaStream()
                                       .peek(method -> method.setAccessible(true))
                                       .map(TestMethod::new);
  }

  private <T> Function<TestMethod<T>, Try<Void>> toTry(Class<? extends T> testClass) {
    return testMethod -> Try.run(() ->
            testMethod.consumer().accept(
                    testClass.newObject()));
  }

  public <T> Map<Method, Try<Void>> runTests(Class<T> testClass) {
    val getAnnotatedMethods = testMethods(testClass);
    return getAnnotatedMethods.apply(Test.class)
                              .map(test -> test.withConsumer(
                                  getAnnotatedMethods.apply(Before.class)
                                                     .map(TestMethod::consumer)
                                                     .reverse()
                                                     .reduce(test.consumer(), CheckedConsumerUtils::after)))
                              .map(test -> test.withConsumer(
                                  getAnnotatedMethods.apply(After.class)
                                                     .map(TestMethod::consumer)
                                                     .reduce(test.consumer(), CheckedConsumer::andThen)))
                              .collect(Collectors.toUnmodifiableMap(TestMethod::method, toTry(testClass)));
  }

  private record TestMethod<T>(Method method, @With CheckedConsumer<T> consumer) {
    public TestMethod(Method method) {
      this(method, method::invoke);
    }
  }
}
