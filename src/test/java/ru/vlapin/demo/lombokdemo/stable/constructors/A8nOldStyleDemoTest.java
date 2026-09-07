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
    val constructor = A8nOldStyleDemo.class.getDeclaredConstructors()[0];
    val annotations = constructor.getDeclaredAnnotations();

    assertThat(annotations).hasSize(3);

    assertThat(constructor.getAnnotation(FirstAnnotation.class)).isNotNull();

    assertThat(constructor.getAnnotation(SecondAnnotation.class)).isNotNull()
        .extracting(SecondAnnotation::value)
        .isEqualTo("value");
  }
}
