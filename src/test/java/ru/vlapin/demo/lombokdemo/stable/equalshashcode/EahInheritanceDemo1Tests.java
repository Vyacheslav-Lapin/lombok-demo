package ru.vlapin.demo.lombokdemo.stable.equalshashcode;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
class EahInheritanceDemo1Tests {

  @Test
  @DisplayName("Inheritance with Lombok equals and hashCode callSuper param works correctly")
  void inheritanceWithLombokEqualsAndHashCodeCallSuperParamWorksCorrectlyTest() {
    // given
    val obj1 = new EahInheritanceDemo1(1, "Lor", true);
    val obj2 = new EahInheritanceDemo1(1, "Lor", true);
    val obj3 = new EahInheritanceDemo1(2, "Lor", true);

    // when
    assertThat(obj1)
        // then
        .isInstanceOf(EahSimpleDemo.class)
        .isEqualTo(obj2)
        .isNotEqualTo(obj3);
  }
}
