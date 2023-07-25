package ru.vlapin.demo.lombokdemo.stable.tostring;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

/**
 * OrderTest.
 */
class OrderTest {

  @Test
  @DisplayName("ToString.Include param rank works correctly")
  void toStringIncludeParamRankWorksCorrectlyTest() {
    assertThat(Order.class)
        .matches(aClass -> Arrays.stream(
                aClass.getDeclaredFields())
            .map(Field::getName)
            .collect(Collectors.joining(", "))
            .equals("x, s"));

    // given
    val obj = new Order(1, "Lor");

    // when
    assertThat(obj)
        // then
        .hasToString(
            "Order(s=Lor, x=1)");
  }
}
