package ru.vlapin.demo.lombokdemo.common.meta;

import static java.lang.annotation.RetentionPolicy.*;
import static org.assertj.core.api.Assertions.*;

import java.lang.annotation.Retention;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.val;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.core.annotation.AnnotatedElementUtils;
import ru.vlapin.demo.lombokdemo.common.TestUtils;

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
 * A test class for validating deep annotation resolution using the `DeepAnnotation` annotation
 * on methods of the `DeepAnnotatedDemo` class.
 * <p>
 * The class uses the JUnit test framework for defining test cases and annotations from
 * `lombok` for streamlined exception handling (`@SneakyThrows`) and declarations.
 * <p>
 * Features in this test include:
 * - Testing the resolution of deep annotations using `findMergedAnnotation`.
 * - Verification of both presence and correctness of the resolved annotation.
 * <p>
 * This class uses:
 * - `@Test` for marking test methods.
 * - `@ExtensionMethod` to leverage the extended utility methods on `AnnotatedElementUtils`.
 * - `@DisplayNameGeneration` to use a custom display name generator via the `TestUtils.ReplaceCamelCase` class
 *   for formatting test method names.
 */
@DisplayNameGeneration(TestUtils.ReplaceCamelCase.class)
@ExtensionMethod(value = AnnotatedElementUtils.class, suppressBaseMethods =false)
class DeepAnnotatedDemoTest {

  @Test
  @SneakyThrows
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
