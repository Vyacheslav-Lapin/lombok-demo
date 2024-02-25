package ru.vlapin.demo.lombokdemo.stable.builder;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RequiredBuilderParamsDemoTest {

  @Test
  @DisplayName("Required builder param works correctly")
  void requiredBuilderParamWorksCorrectlyTest() {
    // given
    val vasya = RequiredBuilderParamsDemo.builder("Vasya").build();

    // when
    assertThat(vasya).isNotNull()
        // then
        .extracting(
            RequiredBuilderParamsDemo::getName,
            RequiredBuilderParamsDemo::getSurname,
            RequiredBuilderParamsDemo::getDob)
        .contains("Vasya", "Pupkin", null);
  }
}
