package ru.vlapin.demo.lombokdemo.stable.cleanup;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.common.TestUtils.*;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
@DisplayNameGeneration(ReplaceCamelCase.class)
class SimpleDemoTest {

  @Configuration
  @ImportAutoConfiguration
  static class Config {
  }

  @Test
  @SneakyThrows
  @DisplayName("db select works correctly")
  void dbSelectWorksCorrectlyTest() {
    assertThat(fromSystemOutPrintln(SimpleJdbcDemo::main)).isNotNull()
                                                          .isEqualTo("""
            Student[id=1, name=Вася Пупкин, groupId=123456]
            Student[id=2, name=Федя Прокопов, groupId=654321]""");
  }
}
