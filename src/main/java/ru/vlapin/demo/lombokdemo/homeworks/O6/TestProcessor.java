package ru.vlapin.demo.lombokdemo.homeworks.O6;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction0;
import io.vavr.Function1;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Try;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;
import ru.vlapin.demo.lombokdemo.common.StreamUtils;

import static java.lang.reflect.Modifier.*;

@UtilityClass
@ExtensionMethod({Arrays.class, StreamUtils.class})
public class TestProcessor {
  public <T> Map<Method, Try<Void>> runTests(Class<T> testExampleClass) {

    val getNewInstance = CheckedFunction0.of(testExampleClass::getConstructor)
                             .andThen(Constructor::newInstance)
                             .unchecked();

    Function1<Class<? extends Annotation>, Stream<Tuple2<Method, CheckedConsumer<T>>>> getAnnotatedMethods =
        annotationClass -> testExampleClass.getDeclaredMethods().stream()
                               .filter(method -> !method.isSynthetic())
                               .filter(method -> !isStatic(method.getModifiers()))
                               .filter(method -> method.isAnnotationPresent(annotationClass))
                               .peek(method -> method.setAccessible(true))
                               .map(method -> Tuple.of(method, CheckedConsumer.of(method::invoke)));

    return getAnnotatedMethods.apply(Test.class)
               .map(test -> test.map2(testMethod -> getAnnotatedMethods.apply(Before.class)
                                                        .map(Tuple2::_2)
                                                        .reverse()
                                                        .reduce(testMethod, (method, beforeMethod) -> beforeMethod.andThen(method))))
               .map(test -> test.map2(testMethod -> getAnnotatedMethods.apply(After.class)
                                                        .map(Tuple2::_2)
                                                        .reduce(testMethod, CheckedConsumer::andThen)))
               .map(test -> test.map2(testMethod -> Try.run(() -> testMethod.accept(getNewInstance.apply()))))
               .collect(Collectors.toUnmodifiableMap(Tuple2::_1, Tuple2::_2));
  }
}
