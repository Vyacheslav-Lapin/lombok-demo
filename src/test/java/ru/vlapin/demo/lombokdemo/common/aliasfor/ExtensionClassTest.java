package ru.vlapin.demo.lombokdemo.common.aliasfor;

import lombok.experimental.ExtensionMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.annotation.AnnotatedElementUtils;

import static org.assertj.core.api.Assertions.*;

//todo 19.07.2023: Написать отдельный класс для теста
@AnnotationChild(extendValue = "extendValue")
@ExtensionMethod({
    AnnotatedElementUtils.class,
})
class ExtensionClassTest {

  @Test
  @DisplayName("AlasFor annotation works correctly")
  void alasForAnnotationWorksCorrectlyTest() {
    assertThat(ExtensionClassTest.class.findMergedAnnotation(AnnotaionBase.class))
        .isNotNull()
        .isInstanceOf(AnnotaionBase.class)
        .extracting(AnnotaionBase::value)
        .isEqualTo("extendValue");
  }
}
