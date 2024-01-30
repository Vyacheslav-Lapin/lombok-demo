package ru.vlapin.demo.lombokdemo.experimental.delegate.cp;

import static lombok.AccessLevel.*;

import io.vavr.CheckedFunction3;
import io.vavr.Function2;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.ExtensionMethod;
import lombok.val;
import org.jetbrains.annotations.Contract;
import ru.vlapin.demo.lombokdemo.common.FileUtils;
import ru.vlapin.demo.lombokdemo.common.Function3Utils;
import ru.vlapin.demo.lombokdemo.common.PropertiesUtils.InitProperties;

@Value
@Getter(NONE)
@InitProperties("db")
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
  public Stream<Connection> get() {

    val connectionSupplier =
        CheckedFunction3.<String, String, String, Connection>of(DriverManager::getConnection)
            .unchecked()
            .supply(url, user, password);

    return Stream.generate(connectionSupplier)
               .limit(poolSize);
  }

  @Contract(" -> new")
  public <T extends Connection> BlockingQueue<T> getSizedBlockingQueue() {
    return new ArrayBlockingQueue<>(poolSize);
  }

  public Stream<Path> getSqlInitFiles() {

    val toFilePath = Function2.<String, String, String>of("/%s/%s.sql"::formatted)
        .apply(initScriptsPath);

    return IntStream.iterate(1, operand -> operand + 1)
               .mapToObj(String::valueOf)
               .map(toFilePath)
               .map(FileUtils::getPathFromFileName)
               .takeWhile(Optional::isPresent)
               .flatMap(Optional::stream);
  }
}
