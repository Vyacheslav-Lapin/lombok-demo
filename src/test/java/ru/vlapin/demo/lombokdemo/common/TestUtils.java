package ru.vlapin.demo.lombokdemo.common;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.Contract;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;
import java.util.function.IntPredicate;

@UtilityClass
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

  @SneakyThrows
  public boolean checkFieldModifier(Class<?> aClass,
                                    String field,
                                    IntPredicate check) {
    return check.test(
        aClass.getDeclaredField(field)
            .getModifiers());
  }
}
