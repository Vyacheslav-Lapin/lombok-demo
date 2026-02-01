package ru.vlapin.demo.lombokdemo.common;

import static org.assertj.core.api.Assertions.*;

import lombok.experimental.ExtensionMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@ExtensionMethod(value = ArrayUtils.class,
                 suppressBaseMethods = false)
@DisplayNameGeneration(ReplaceCamelCase.class)
class ArrayUtilsTest {

  @Test
  //@lombok.SneakyThrows
  @DisplayName("add method works correctly")
  void addMethodWorksCorrectlyTest() {
    // given
    @SuppressWarnings("MismatchedReadAndWriteOfArray")
    Integer[] integers = {1, 2, 3};
    // when
    assertThat(integers.add(4)).isNotNull()
        // then
        .containsExactly(1, 2, 3, 4);

    // when
    assertThat(integers.add(4, 5)).isNotNull()
        // then
        .containsExactly(1, 2, 3, 4, 5);
  }
}
