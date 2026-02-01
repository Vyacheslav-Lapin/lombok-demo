package ru.vlapin.demo.lombokdemo.experimental.delegate.cp;

import static com.github.dockerjava.api.model.Ports.Binding.*;
import static org.assertj.core.api.Assertions.*;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import io.vavr.CheckedFunction1;
import io.vavr.CheckedFunction2;
import java.sql.ResultSet;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@Testcontainers(disabledWithoutDocker = true)
@DisplayNameGeneration(ReplaceCamelCase.class)
class ConnectionPoolTests {

  @Container
  @SuppressWarnings({"resource", "unused"})
  static PostgreSQLContainer<?> postgreSQLContainer =
      new PostgreSQLContainer<>("postgres:12")
          .withDatabaseName("postgres")
          .withUsername("postgres")
          .withPassword("postgres")
          .withReuse(true)
          .withExposedPorts(5432)
          .withCreateContainerCmdModifier(
              cmd -> cmd.withHostConfig(
                  new HostConfig()
                      .withPortBindings(
                          new PortBinding(bindPort(5432),
                                          new ExposedPort(5432)))));
  static String SQL = """
      -- noinspection SqlNoDataSourceInspection,SqlResolve
      select id, first_name, last_name, permission, dob, email, password, address, telephone
      from Person""";

  @Test
  @SneakyThrows
//  @DisplayName("Connection Pool works correctly")
  void connectionPoolWorksCorrectlyTest() {
    @Cleanup val connectionPool = ConnectionPool.getPool();
    @Cleanup val connection = connectionPool.get();
    @Cleanup val statement = connection.createStatement();

    //noinspection SqlSourceToSinkFlow
    @Cleanup val resultSet = statement.executeQuery(SQL);

    assertThat(resultSet).isNotNull()
        .matches(CheckedFunction1.of(ResultSet::next).unchecked()::apply)
        .extracting(CheckedFunction2.<ResultSet, String, String>of(ResultSet::getString).unchecked()::apply)
        .extracting(getString -> getString.apply("first_name"))
        .isEqualTo("Jose");
  }
}
