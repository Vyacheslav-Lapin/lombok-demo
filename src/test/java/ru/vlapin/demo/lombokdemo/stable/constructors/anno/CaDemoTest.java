package ru.vlapin.demo.lombokdemo.stable.constructors.anno;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * CaDemoTest.
 */
class CaDemoTest {

  @Test
  @DisplayName("Annotation marked constructor param correctly")
  void annotationMarkedConstructorParamCorrectlyTest() {
    // given
    val constrParam = CaDemo.class
        .getConstructors()[0]
        .getParameters()[0];

    // when
    assertThat(constrParam)
        // then
        .matches(param ->
            param.isAnnotationPresent(
                MyAnno.class));
  }

  @Test
  @SneakyThrows
  @DisplayName("Annotation marked setter param correctly")
  void annotationMarkedSetterParamCorrectlyTest() {
    // given
    val setterParam = CaDemo.class
        .getMethod("setX", int.class)
        .getParameters()[0];

    // when
    assertThat(setterParam)
        // then
        .matches(param ->
            param.isAnnotationPresent(MyAnno.class));
  }

  @Test
  @SneakyThrows
  @DisplayName("Annotation marked wither param correctly")
  void annotationMarkedWitherParamCorrectlyTest() {
    // given
    val setterParam = CaDemo.class
        .getMethod("withX", int.class)
        .getParameters()[0];

    // when
    assertThat(setterParam)
        // then
        .matches(param ->
            param.isAnnotationPresent(MyAnno.class));
  }

  @Test
  @SneakyThrows
  @DisplayName("Annotation marked builder setter param correctly")
  void annotationMarkedBuilderSetterParamCorrectlyTest() {
    // given
    val builderSetterParam = CaDemo.CaDemoBuilder.class
        .getMethod("x", int.class)
        .getParameters()[0];

    // when
    assertThat(builderSetterParam)
        // then
        .matches(param ->
            param.isAnnotationPresent(MyAnno.class));
  }
}
