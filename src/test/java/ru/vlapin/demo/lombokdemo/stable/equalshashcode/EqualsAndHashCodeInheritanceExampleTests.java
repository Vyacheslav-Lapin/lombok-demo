package ru.vlapin.demo.lombokdemo.stable.equalshashcode;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class EqualsAndHashCodeInheritanceExampleTests {

  @Test
  @SneakyThrows
  @DisplayName("Inheritance with Lombok equals and hashCode works correctly")
  void inheritanceWithLombokEqualsAndHashCodeWorksCorrectlyTest() {
    // Тест:
    val example1 = new EqualsAndHashCodeInheritanceExample(1, "Lorem ipsum dolor sit amet", true);
    val example2 = new EqualsAndHashCodeInheritanceExample(1, "Lorem ipsum dolor sit amet", true);
    assertThat(example1).isNotNull()
        .isNotNull()
        .isInstanceOf(EqualsAndHashCodeSimpleExample.class)
        .isEqualTo(example2);
  }
}
