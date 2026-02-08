package ru.vlapin.demo.lombokdemo.common;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction1;
import java.io.InputStream;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;

@UtilityClass
@ExtensionMethod(value = {
    FileUtils.class,
    Objects.class,
}, suppressBaseMethods = false)
public class InputStreamUtils {

  /**
   * Maps a file input stream to a result by applying a provided checked function.
   *
   * @param <T>        the type of the result returned by the function
   * @param fileName   the name of the file to be read
   * @param fisMapper  a checked function that processes the {@link InputStream} and produces a result
   * @return           the result of applying the provided checked function to the file input stream
   */
  @SuppressWarnings("unused")
  public <T> T mapFileInputStreamChecked(String fileName,
                                         CheckedFunction1<? super InputStream, ? extends T> fisMapper) {
    return mapFileInputStream(fileName, fisMapper.unchecked());
  }

  /**
   * Maps a file input stream to a result by applying a provided function.
   *
   * @param <T>       the type of the result returned by the function
   * @param fileName  the name of the file to be read
   * @param fisMapper a function that processes the {@link InputStream} and produces a result
   * @return          the result of applying the provided function to the file input stream
   */
  @SneakyThrows
  public <T> T mapFileInputStream(String fileName,
                                  Function<? super InputStream, ? extends T> fisMapper) {

    @Cleanup val inputStream =
        InputStreamUtils.class.getResourceAsStream(
                fileName.adoptFileName())
            .requireNonNull();

    return fisMapper.apply(inputStream);
  }

  /**
   * Executes the provided checked consumer with a file input stream for the specified file.
   * This method wraps the checked consumer in an unchecked consumer and passes it
   * to the overloaded method that handles the input stream via a try-with-resources block.
   *
   * @param fileName    the name of the file to be opened as a file input stream
   * @param fisConsumer a checked consumer that processes the {@link InputStream}
   */
  public void withFileInputStreamChecked(String fileName,
                                         CheckedConsumer<? super InputStream> fisConsumer) {
    withFileInputStream(fileName, fisConsumer.unchecked());
  }

  /**
   * Executes the provided consumer with an {@link InputStream} for the specified file.
   * The file is opened as an input stream and provided to the consumer for processing.
   * The input stream is automatically closed after the consumer completes its task.
   *
   * @param fileName    the name of the file to be opened as an input stream
   * @param fisConsumer a consumer that processes the {@link InputStream}
   */
  @SneakyThrows
  public void withFileInputStream(String fileName,
                                  Consumer<? super InputStream> fisConsumer) {
    @Cleanup val inputStream =
        InputStreamUtils.class.getResourceAsStream(
                fileName.adoptFileName())
            .requireNonNull();

    fisConsumer.accept(inputStream);
  }
}
