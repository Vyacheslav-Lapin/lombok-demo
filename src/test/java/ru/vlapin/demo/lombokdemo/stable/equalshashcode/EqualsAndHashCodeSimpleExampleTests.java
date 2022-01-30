package ru.vlapin.demo.lombokdemo.stable.equalshashcode;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class EqualsAndHashCodeSimpleExampleTests {

  @Test
  @SneakyThrows
  @DisplayName("Equals method works correctly")
  void equalsMethodWorksCorrectlyTest() {
    val example1 = new EqualsAndHashCodeSimpleExample(5, "Lorem ipsum dolor sit amet");
    val example2 = new EqualsAndHashCodeSimpleExample(5, "Lorem ipsum dolor sit amet");
    val example3 = new EqualsAndHashCodeSimpleExample(5, "Lorem ipsum dolor sit amet");
    assertThat(example1).isNotNull()
        // 1. Рефлексивность - объект всегда равен самому себе
        .isEqualTo(example1)
        // 2. Not-nullable - при сравнении с null всегда возвращаем false
        .isNotEqualTo(null)
        // 3. Симметричность - эквивалентность работает в обе стороны
        .isEqualTo(example2)
        .matches(__ -> example2.equals(example1))
        // 4. Транзитивность – эквивалентность x`а и y`а, а также y`а и z`а означает эквивалентность x и z`а для любых x, y, z
        .matches(__ -> example2.equals(example3))
        .isEqualTo(example3);
    // 5. Непротиворечивость – при многократном вызове метода для двух не подвергшихся существенному изменению (!) за это время объектов возвращаемое значение всегда должно быть одинаковым
  }
}
