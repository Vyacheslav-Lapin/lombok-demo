package ru.vlapin.demo.lombokdemo.experimental.delegate.cp;

import static java.util.stream.Collectors.*;

import io.vavr.Function2;
import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.function.Supplier;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.NonFinal;
import lombok.experimental.StandardException;
import lombok.val;
import ru.vlapin.demo.lombokdemo.common.FileUtils;
import ru.vlapin.demo.lombokdemo.common.PropertiesUtils;

@ExtensionMethod(suppressBaseMethods = false,
                 value = {
                     FileUtils.class,
                     PropertiesUtils.class,
                 })
public class ConnectionPool implements Closeable, Supplier<Connection> {

  private static final Map<String, ConnectionPool> instances = new HashMap<>();
  BlockingQueue<PooledConnection> connectionQueue;

  @NonFinal
  volatile boolean opened = true;

  @SneakyThrows
  private ConnectionPool(String fileName) {

    val connectionFactory = ConnectionFactory.class.getInstance();

    val pooledConnectionFactory =
        Function2.of(PooledConnection::new)
            .apply(this::closePolledConnection);

    connectionQueue = connectionFactory.get()
        .map(pooledConnectionFactory)
        .collect(toCollection(connectionFactory::getSizedBlockingQueue));

    //init
    val sql = connectionFactory.getSqlInitFiles()
        .map(file -> file.getFileAsString("\n"))
        .collect(joining("\n\n"));

    @Cleanup val connection = get();
    @Cleanup val statement = connection.createStatement();

    //noinspection SqlSourceToSinkFlow
    statement.executeUpdate(sql);

    if (!connection.getAutoCommit())
      connection.commit();
  }

  public static ConnectionPool getPoolFor(String dbConfig) {
    return instances.computeIfAbsent(dbConfig, ConnectionPool::new);
  }

  public static Connection getConnection() {
    return getPool().get();
  }

  public static ConnectionPool getPool() {
    return getPoolFor("db");
  }

  @SneakyThrows
  private void closePolledConnection(PooledConnection pooledConnection) {
    if (!opened) pooledConnection.reallyClose();

    if (pooledConnection.isClosed())
      throw new ConnectionPoolException("Attempting to close closed connection.");

    if (pooledConnection.isReadOnly())
      pooledConnection.setReadOnly(false);

    if (!pooledConnection.getAutoCommit())
      pooledConnection.setAutoCommit(true);

    if (!connectionQueue.offer(pooledConnection))
      throw new ConnectionPoolException(
          new SQLException("Error allocating connection in the pool."));
  }

  @Override
  public void close() {
    opened = false;
    connectionQueue.forEach(PooledConnection::reallyClose);
  }

  @Override
  @SuppressWarnings("java:S2142")
  public Connection get() {
    try {
      return connectionQueue.take();
    } catch (InterruptedException e) {
      throw new ConnectionPoolException(
          "Error connecting to the data source.", e);
    }
  }
}

@StandardException
class ConnectionPoolException extends RuntimeException {
}
