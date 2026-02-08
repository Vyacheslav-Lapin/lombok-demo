package ru.vlapin.demo.lombokdemo.common.meta.aliasfor;

import static org.assertj.core.api.Assertions.*;

import lombok.experimental.ExtensionMethod;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.core.annotation.AnnotatedElementUtils;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

//todo 19.07.2023: Написать отдельный класс для теста
@ChildAnnotation(7)
@DisplayNameGeneration(ReplaceCamelCase.class)
@ExtensionMethod(value = AnnotatedElementUtils.class, suppressBaseMethods =false)
class ExtensionClassTest {

  @Test
  @DisplayName("AlasFor annotation works correctly")
  void alasForAnnotationWorksCorrectlyTest() {
    // given
    val innerAlias = getClass()
        .findMergedAnnotation(InnerAlias.class);

    //when
    assertThat(innerAlias)
        // then
        .isNotNull()
        .isInstanceOf(InnerAlias.class)
        .extracting(InnerAlias::x)
        .isEqualTo(7);
  }
}
