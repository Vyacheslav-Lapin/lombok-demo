package ru.vlapin.demo.lombokdemo;

import static java.lang.ScopedValue.*;
import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.common.TestUtils.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceCamelCase.class)
class ScopedValueTest {

  private static final ScopedValue<String> currentFruit = ScopedValue.newInstance();

  void printFruit() {
    System.out.printf("Fruit: %s%n", currentFruit.get());
  }

  @Test
  @DisplayName("ScopedValue works correctly")
  void scopedValueWorksCorrectlyTest() {
    // given

    // when
    assertThat(fromSystemOutPrintln(() -> where(currentFruit, "banana").run(this::printFruit)))
                                    // then
                                    .isNotNull()
                                    .isEqualTo("Fruit: banana");
    // when
    assertThat(fromSystemOutPrintln(() -> where(currentFruit, "apple").run(this::printFruit)))
                                    // then
                                    .isNotNull()
                                    .contains("Fruit: apple");
  }
}
