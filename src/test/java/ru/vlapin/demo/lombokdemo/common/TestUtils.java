package ru.vlapin.demo.lombokdemo.common;

import io.vavr.CheckedFunction2;
import io.vavr.CheckedFunction3;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.Contract;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

@UtilityClass
@ExtensionMethod({
    PredicateUtils.class,
})
public class TestUtils {

  public final String LINE_SEPARATOR = System.lineSeparator();
  public final String TEST_RESOURCES_PATH = "./src/test/resources/";

  @Contract(pure = true)
  private String fromPrintStream(Consumer<? super PrintStream> printStreamConsumer) {
    val out = new ByteArrayOutputStream();
    @Cleanup val printStream = new PrintStream(out);
    printStreamConsumer.accept(printStream);
    return out.toString().intern();
  }

  @SneakyThrows
  @Contract(value = "_ -> new", pure = true)
  public String fromSystemOutPrint(Runnable task) {
    return fromPrintStream(printStream -> {
      val realOut = System.out;
      System.setOut(printStream);
      task.run();
      System.setOut(realOut);
    });
  }

  @Contract(pure = true)
  public String fromSystemOutPrintln(Runnable runnable) {
    val s = fromSystemOutPrint(runnable);
    return s.endsWith(LINE_SEPARATOR) ?
        s.substring(0, s.length() - LINE_SEPARATOR.length()) :
        s;
  }

  @Contract(pure = true)
  @SuppressWarnings("unused")
  public String toTestResourceFilePath(String fileName) {
    return TEST_RESOURCES_PATH + fileName;
  }

  public Function<Class<?>, Field> declaredFieldGet(String fieldName) {
    return CheckedFunction2.<Class<?>, String, Field>of(Class::getDeclaredField)
        .unchecked()
        .reversed()
        .apply(fieldName);
  }

  public Function<Class<?>, Method> declaredMethodGet(String methodName, Class<?>[] args) {
    return CheckedFunction3.<Class<?>, String, Class<?>[], Method>of(Class::getDeclaredMethod)
        .unchecked()
        .reversed()
        .apply(args, methodName);
  }

  public Function<Class<?>, Method> declaredMethodGet(String methodName) {
    return declaredMethodGet(methodName, new Class<?>[]{});
  }

  public Predicate<Class<?>> fieldModifierCheck(String fieldName,
                                                IntPredicate check) {
    return check
        .compose(Field::getModifiers)
        .compose(declaredFieldGet(fieldName));
  }

  public <T extends Executable> Predicate<T> executableModifierCheck(IntPredicate check) {
//    return check.compose(Executable::getModifiers); //todo 04.08.2023: баг IDEA'и - написать issue
    return PredicateUtils.compose(check, Executable::getModifiers);
  }

  public Predicate<Class<?>> methodModifierCheck(String methodName,
                                                 Class<?>[] argTypes,
                                                 IntPredicate check) {
    return check
        .compose(Method::getModifiers)
        .compose(declaredMethodGet(methodName, argTypes));
  }

  public Predicate<Class<?>> methodModifierCheck(String methodName,
                                                 IntPredicate check) {
    return check
        .compose(Method::getModifiers)
        .compose(declaredMethodGet(methodName));
  }
}
