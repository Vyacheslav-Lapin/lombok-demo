package ru.vlapin.demo.lombokdemo.common.meta;

import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.core.annotation.AnnotatedElementUtils;
import ru.vlapin.demo.lombokdemo.common.TestUtils;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.*;
import static org.assertj.core.api.Assertions.*;

@Retention(RUNTIME)
@interface DeepAnnotation {
  String value();
}

@Retention(RUNTIME)
@DeepAnnotation("lor")
@interface MetaAnnotation {
}

@SuppressWarnings("java:S1186")
class DeepAnnotatedDemo {
  @MetaAnnotation public void m() {
  }
}

/**
 * DeepAnnotatedDemoTest.
 */
@ExtensionMethod({
    AnnotatedElementUtils.class,
})
@DisplayNameGeneration(TestUtils.ReplaceCamelCase.class)
class DeepAnnotatedDemoTest {

  @Test
  @SneakyThrows
  @DisplayName("Deep annotation finds correctly")
  void deepAnnotationFindsCorrectlyTest() {
    // given
    val deepAnnotation = DeepAnnotatedDemo.class
        .getMethod("m")
        .findMergedAnnotation(DeepAnnotation.class);
    
    // when
    assertThat(deepAnnotation).isNotNull()
        // then
        .isInstanceOf(DeepAnnotation.class)
        .extracting(DeepAnnotation::value)
        .isEqualTo("lor");
  }
}
