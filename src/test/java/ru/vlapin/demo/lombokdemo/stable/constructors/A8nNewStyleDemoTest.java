package ru.vlapin.demo.lombokdemo.stable.constructors;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.FirstAnnotation;
import ru.vlapin.demo.lombokdemo.SecondAnnotation;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * A8nNewStyleDemoTest.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class A8nNewStyleDemoTest {

  @Test
  @DisplayName("Constructor marked by annotations correctly new style")
  void constructorMarkedByAnnotationsCorrectlyNewStyleTest() {
    // given
    val annotations =
        A8nNewStyleDemo.class
            .getDeclaredConstructors()[0]
            .getDeclaredAnnotations();

    // when
    assertThat(annotations)
        // then
        .hasSize(2)
        .matches(__ -> annotations[0] instanceof FirstAnnotation);

    // when
    assertThat(annotations[1]).isNotNull()
        // then
        .isInstanceOf(SecondAnnotation.class)
        .extracting(SecondAnnotation.class::cast)
        .extracting(SecondAnnotation::value)
        .isEqualTo("value");
  }
}
