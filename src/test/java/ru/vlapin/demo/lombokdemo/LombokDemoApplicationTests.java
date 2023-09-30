package ru.vlapin.demo.lombokdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest
@org.testcontainers.junit.jupiter.Testcontainers
class LombokDemoApplicationTests {

  @Container
  @ServiceConnection
  @SuppressWarnings("unused")
  static PostgreSQLContainer<?> postgreSQLContainer =
      new PostgreSQLContainer<>("postgres:latest");

  @Test
  @SuppressWarnings("java:S2699")
  void contextLoads() {
  }
}
