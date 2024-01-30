package ru.vlapin.demo.lombokdemo.experimental.delegate.cp;

import java.sql.Connection;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.Delegate;

@AllArgsConstructor
public class PooledConnection implements Connection {

  Consumer<? super PooledConnection> closer;

  @Delegate
  Connection connection;

  @Override
  public void close() {
    closer.accept(this);
  }

  @SneakyThrows
  public final void reallyClose() {
    connection.close();
  }
}
