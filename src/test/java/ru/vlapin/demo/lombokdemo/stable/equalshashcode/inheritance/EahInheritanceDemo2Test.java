package ru.vlapin.demo.lombokdemo.stable.equalshashcode.inheritance;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;
import ru.vlapin.demo.lombokdemo.stable.equalshashcode.EahSimpleDemo;

/**
 * EahInheritanceDemo2Test.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class EahInheritanceDemo2Test {

  @Test
  @DisplayName("Inheritance with Lombok equals and hashCode callSuper config param works correctly")
  void inheritanceWithLombokEqualsAndHashCodeCallSuperConfigParamWorksCorrectlyTest() {
    // given
    val obj1 = new EahInheritanceDemo2(1, "Lor", true);
    val obj2 = new EahInheritanceDemo2(1, "Lor", true);
    val obj3 = new EahInheritanceDemo2(2, "Lor", true);

    // when
    assertThat(obj1)
        // then
        .isInstanceOf(EahSimpleDemo.class)
        .isEqualTo(obj2)
        .isNotEqualTo(obj3);
  }
}
