package ru.vlapin.demo.lombokdemo.experimental.delegate.cp;

import static org.assertj.core.api.Assertions.*;

import io.vavr.CheckedFunction1;
import io.vavr.CheckedFunction2;
import java.sql.ResultSet;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@SpringBootTest
@DisplayNameGeneration(ReplaceCamelCase.class)
class ConnectionPoolTests {

  @Configuration
  @ImportAutoConfiguration
  static class Config {
  }

  static String SQL = """
      -- noinspection SqlNoDataSourceInspection,SqlResolve
      select id, first_name, last_name, permission, dob, email, password, address, telephone
      from Person""";

  @Test
  @SneakyThrows
  @DisplayName("Connection Pool works correctly")
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
