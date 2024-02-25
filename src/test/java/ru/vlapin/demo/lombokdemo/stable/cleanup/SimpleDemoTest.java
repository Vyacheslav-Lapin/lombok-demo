package ru.vlapin.demo.lombokdemo.stable.cleanup;

import static com.github.dockerjava.api.model.Ports.Binding.*;
import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.common.TestUtils.*;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
class SimpleDemoTest {

  @Container
  @SuppressWarnings({"resource", "unused"})
  static PostgreSQLContainer<?> postgreSQLContainer =
      new PostgreSQLContainer<>("postgres:latest")
          .withDatabaseName("postgres")
          .withUsername("postgres")
          .withPassword("postgres")
//          .withReuse(true)
          .withExposedPorts(5432)
          .withCreateContainerCmdModifier(
              cmd -> cmd.withHostConfig(
                  new HostConfig()
                      .withPortBindings(
                          new PortBinding(bindPort(5432),
                                          new ExposedPort(5432)))));

  @Test
  @SneakyThrows
  @DisplayName("db select works correctly")
  void dbSelectWorksCorrectlyTest() {
    assertThat(fromSystemOutPrintln(SimpleDemo::main)).isNotNull()
        .isEqualTo("""
            Student[id=1, name=Вася Пупкин, groupId=123456]
            Student[id=2, name=Федя Прокопов, groupId=654321]""");
  }
}
