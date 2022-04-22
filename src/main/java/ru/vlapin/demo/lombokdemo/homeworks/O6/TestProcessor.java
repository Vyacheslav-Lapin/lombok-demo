package ru.vlapin.demo.lombokdemo.homeworks.O6;

import java.util.Arrays;

import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;

@UtilityClass
@ExtensionMethod(Arrays.class)
public class TestProcessor {

//  private <T> Function<Class<T>, Consumer<T>> GET_BEFORE =
//  private Function<Class<?>, Consumer<?>> GET_BEFORE = aClass ->
//      aClass.getDeclaredMethods()
//          .stream()

//  private Method[] GET_AFTER
//
//  public Stream<Try<Boolean>> main(String... classNames) {
//    return classNames.stream()
//        .map(ReflectionUtils::toClass)
//        .map(Class::getDeclaredMethods)
//        .flatMap(Arrays::stream)
//        .filter(method -> method.isAnnotationPresent(Test.class))
//        .map(TestProcessor::callTest);
//  }
//
//  private Try<Boolean> callTest(Method testMethod) {
//    val declaringClass = testMethod.getDeclaringClass();
//    try {
//      val object = declaringClass.getConstructor().newInstance();
//
//    }
//    catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//      e.printStackTrace();
//      return Try.failure(e);
//    }
//  }
}
