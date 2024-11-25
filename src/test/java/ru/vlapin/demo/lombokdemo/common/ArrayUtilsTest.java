package ru.vlapin.demo.lombokdemo.common;

import static org.assertj.core.api.Assertions.*;

import lombok.experimental.ExtensionMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@ExtensionMethod(value = ArrayUtils.class,
                 suppressBaseMethods = false)
class ArrayUtilsTest {

  @Test
  //@lombok.SneakyThrows
  @DisplayName("add method works correctly")
  void addMethodWorksCorrectlyTest() {
    // given
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
