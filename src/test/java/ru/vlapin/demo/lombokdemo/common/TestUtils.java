package ru.vlapin.demo.lombokdemo.common;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class TestUtils {

  public final String LINE_SEPARATOR = System.lineSeparator();
  public final String TEST_RESOURCES_PATH = "./src/test/resources/";

  @NotNull
  @Contract(pure = true)
  private String fromPrintStream(@NotNull Consumer<PrintStream> printStreamConsumer) {
    val out = new ByteArrayOutputStream();
    @Cleanup val printStream = new PrintStream(out);
    printStreamConsumer.accept(printStream);
    return out.toString().intern();
  }

  @NotNull
  @SneakyThrows
  @Contract(value = "_ -> new", pure = true)
  public String fromSystemOutPrint(@NotNull Runnable task) {
    return fromPrintStream(printStream -> {
      val realOut = System.out;
      System.setOut(printStream);
      task.run();
      System.setOut(realOut);
    });
  }

  @NotNull
  @Contract(pure = true)
  public String fromSystemOutPrintln(@NotNull Runnable runnable) {
    val s = fromSystemOutPrint(runnable);
    return s.endsWith(LINE_SEPARATOR) ?
               s.substring(0, s.length() - LINE_SEPARATOR.length()) :
               s;
  }

  @NotNull
  @Contract(pure = true)
  @SuppressWarnings("unused")
  public String toTestResourceFilePath(@NotNull String fileName) {
    return TEST_RESOURCES_PATH + fileName;
  }
}
