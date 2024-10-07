package ru.vlapin.demo.lombokdemo.homeworks.O6;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.common.TestUtils.*;

import io.vavr.control.Try;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import org.junit.jupiter.api.DisplayName;

@ExtensionMethod({
    TestProcessor.class,
})
class TestProcessorTests {

  @SneakyThrows
//  @Disabled
  @org.junit.jupiter.api.Test
  @DisplayName("Test framework works in right order")
  void testFrameworkWorksInRightOrderTest() {
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
                       after2
                       before1
                       before2""");
  }

  @SneakyThrows
  @org.junit.jupiter.api.Test
  @DisplayName("Test framework works correctly")
  void testFrameworkWorksCorrectlyTest() {

    Method[] tests = {
        TestExample.class.getDeclaredMethod("test1"),
        TestExample.class.getDeclaredMethod("test2"),
        TestExample.class.getDeclaredMethod("test3"),
    };

    assertThat(TestExample.class.runTests()).isNotNull()
        .hasSize(3)
        .containsOnlyKeys(tests)
        .extractingByKeys(tests)
        .matches(tries -> tries.get(0).isSuccess())
        .matches(tries -> tries.get(1).isSuccess())
        .element(2)
        .matches(Try::isFailure)
        .extracting(Try::getCause)
        .isInstanceOf(InvocationTargetException.class)
        .extracting(Throwable::getCause)
        .isInstanceOf(RuntimeException.class)
        .extracting(Throwable::getMessage)
        .isEqualTo("Boom!");
  }
}

// @formatter:off
@SuppressWarnings("unused")
public class TestExample {
  @Before void before1() { System.out.println("before1"); }
  @Before void before2() { System.out.println("before2"); }
  @Test void test1() { System.out.println("test1"); }
  @Test void test2() { System.out.println("test2"); }
  @Test void test3() { throw new RuntimeException("Boom!"); }
  @After void after1() { System.out.println("after1"); }
  @After void after2() { System.out.println("after2"); }
}
// @formatter:on
