package ru.vlapin.demo.lombokdemo.stable.equalshashcode;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
class EahSimpleDemoTests {

  @Test
  @SuppressWarnings("EqualsWithItself")
  @DisplayName("Equals method works correctly")
  void equalsMethodWorksCorrectlyTest() {

    val obj1 = new EahSimpleDemo(5, "Lor");
    val obj2 = new EahSimpleDemo(5, "Lor");
    val obj3 = new EahSimpleDemo(5, "Lor");

    assertThat(obj1)

        // 1. Рефлексивность - объект всегда равен самому себе
        .isEqualTo(obj1)

        // 2. Not-nullable - при сравнении с null всегда возвращаем false
        .isNotEqualTo(null)

        // 3. Симметричность - эквивалентность работает в обе стороны
        .isEqualTo(obj2)
        .matches(_ -> obj2.equals(obj1))

        // 4. Транзитивность – эквивалентность x`а и y`а, а также y`а и z`а означает эквивалентность x и z`а для любых x, y, z
        .matches(_ -> obj2.equals(obj3))
        .isEqualTo(obj3)

        // HashCode
        .hasSameHashCodeAs(obj2)
        .hasSameHashCodeAs(obj3);

    // 5. Непротиворечивость – при многократном вызове метода для двух не подвергшихся существенному изменению (!) за это время объектов возвращаемое значение всегда должно быть одинаковым
  }
}
