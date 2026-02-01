package ru.vlapin.demo.lombokdemo.stable.setter;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * Установленная над классом аннотация считается по умолчанию установленной
 * над всеми его полями.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class MarkedClassExampleTest {

  @Test
  @DisplayName("marked class setter works correctly")
  void markedClassSetterWorksCorrectlyTest() {
    // given
    val mce = new MarkedClassExample();

    // when
    mce.setX(1);
    mce.setY(2);
    mce.setZ(3);

    // then
    assertThat(mce).isNotNull()
        .hasFieldOrPropertyWithValue("x", 1)
        .hasFieldOrPropertyWithValue("y", 2)
        .hasFieldOrPropertyWithValue("z", 3);
  }
}
