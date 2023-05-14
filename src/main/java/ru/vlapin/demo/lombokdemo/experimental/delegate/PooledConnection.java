package ru.vlapin.demo.lombokdemo.experimental.delegate;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.Delegate;

import java.io.Closeable;
import java.sql.Connection;
import java.util.function.Consumer;

@AllArgsConstructor
public class PooledConnection implements Connection {

  Consumer<? super PooledConnection> closer;

  @Delegate(excludes = Closeable.class)
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
