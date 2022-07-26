package ru.vlapin.demo.lombokdemo.homeworks.O6;

import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.common.TestUtils.*;

@ExtensionMethod(TestProcessor.class)
class TestProcessorTests {

  @SneakyThrows
  @org.junit.jupiter.api.Test
  @DisplayName("Test framework works correctly")
  void testFrameworkWorksCorrectlyTest() {
    assertThat(fromSystemOutPrintln(() -> TestExample.class.runTests())).isNotNull()
        .isEqualTo("""
                       before1
                       before2
                       test1
                       after1
                       after2
                       before1
                       before2
                       test2
                       after1
                       after2""");
  }
}

public class TestExample {

  @Before
  void before1() {
    System.out.println("before1");
  }

  @Before
  void before2() {
    System.out.println("before2");
  }

  @Test
  void test1() {
    System.out.println("test1");
  }

  @Test
  void test2() {
    System.out.println("test2");
  }

  @After
  void after1() {
    System.out.println("after1");
  }

  @After
  void after2() {
    System.out.println("after2");
  }
}
