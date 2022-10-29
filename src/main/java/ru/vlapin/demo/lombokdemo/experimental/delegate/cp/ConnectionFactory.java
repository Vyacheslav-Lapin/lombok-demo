package ru.vlapin.demo.lombokdemo.experimental.delegate.cp;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import io.vavr.CheckedFunction3;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.Value;
import lombok.experimental.ExtensionMethod;
import lombok.val;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.vlapin.demo.lombokdemo.common.FileUtils;
import ru.vlapin.demo.lombokdemo.common.Function3Utils;

import static lombok.AccessLevel.*;

@Value
@Getter(NONE)
@ExtensionMethod({
    Function3Utils.class,
})
public class ConnectionFactory implements Supplier<Stream<Connection>> {

  String url;
  String user;
  String password;

  int poolSize;

  String initScriptsPath;

  @Override
  @SneakyThrows
  public Stream<Connection> get() {

    val connectionSupplier =
        CheckedFunction3.<String, String, String, Connection>of(DriverManager::getConnection)
            .unchecked()
            .supply(url, user, password);

    return Stream.generate(connectionSupplier)
        .limit(poolSize);
  }

  @NotNull
  @Contract(" -> new")
  public <T extends Connection> BlockingQueue<T> getSizedBlockingQueue() {
    return new ArrayBlockingQueue<>(poolSize);
  }

  public Stream<Path> getSqlInitFiles() {
    return IntStream.iterate(1, operand -> operand + 1)
        .mapToObj(String::valueOf)
        .map(fileName -> String.format("/%s/%s.sql", initScriptsPath, fileName))
        .map(FileUtils::getPathFromFileName)
        .takeWhile(Optional::isPresent)
        .flatMap(Optional::stream);
  }

}
