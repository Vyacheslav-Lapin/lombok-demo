package ru.vlapin.demo.lombokdemo.stable.cleanup;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.common.TestUtils.*;

class SimpleDemoTest {
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
