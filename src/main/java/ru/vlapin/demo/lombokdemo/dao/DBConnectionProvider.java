package ru.vlapin.demo.lombokdemo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

//@ConfigurationProperties("spring.datasource")
@RequiredArgsConstructor(onConstructor_ = @ConstructorBinding)
public class DBConnectionProvider {
  String url;
  String username;
  String password;

  @SneakyThrows
  public Connection getConnection() {
    return DriverManager.getConnection(url, username, password);
  }
}
