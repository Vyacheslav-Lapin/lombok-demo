package ru.vlapin.demo.lombokdemo.stable.data;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.*;

/**
 * DataCustomizationDemoTest.
 */
class DataCustomizationDemoTest {

  @Test
  @DisplayName("Data meta-annotation customization works correctly 1")
  void dataMetaAnnotationCustomizationWorksCorrectly1Test() {
    // given
    val demo1 = new DataCustomizationDemo(
        "lor").setX(1);

    val demo2 = new DataCustomizationDemo(
        "lor").setX(2);

    assertThat(demo1).isEqualTo(demo2);
  }

  @Test
  @SneakyThrows
  @DisplayName("Data meta-annotation customization works correctly 2")
  void dataMetaAnnotationCustomizationWorksCorrectly2Test() {
    // given
    val setX = DataCustomizationDemo.class
        .getDeclaredMethod("setX", int.class);

    assertThat(setX).isNotNull()
        .extracting(Method::getModifiers)
        .matches(Modifier::isProtected);
  }
}
