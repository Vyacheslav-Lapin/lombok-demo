package ru.vlapin.demo.lombokdemo.stable.constructors;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.FirstAnnotation;
import ru.vlapin.demo.lombokdemo.SecondAnnotation;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * A8nOldStyleDemoTest.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class A8nOldStyleDemoTest {

  @Test
  //@DisplayName("Constructor marked by annotations correctly old style")
  void constructorMarkedByAnnotationsCorrectlyOldStyleTest() {
    // given
    val annotations =
        A8nOldStyleDemo.class
            .getDeclaredConstructors()[0]
            .getDeclaredAnnotations();

    assertThat(annotations).hasSize(2);

    assertThat(annotations[0]).isNotNull()
        .isInstanceOf(FirstAnnotation.class);

    assertThat(annotations[1]).isNotNull()
        .isInstanceOf(SecondAnnotation.class)
        .extracting(SecondAnnotation.class::cast)
        .extracting(SecondAnnotation::value)
        .isEqualTo("value");
  }
}
