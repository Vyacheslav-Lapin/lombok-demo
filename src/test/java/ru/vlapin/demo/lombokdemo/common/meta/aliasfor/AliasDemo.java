package ru.vlapin.demo.lombokdemo.common.meta.aliasfor;

import static java.lang.annotation.RetentionPolicy.*;
import static org.assertj.core.api.Assertions.*;

import java.lang.annotation.Retention;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.core.annotation.AliasFor;
import org.springframework.core.annotation.AnnotatedElementUtils;
import ru.vlapin.demo.lombokdemo.common.TestUtils;

@Retention(RUNTIME)
@interface DeepAnnotation {
  @AliasFor("value") String param1() default "";

  @AliasFor("param1") String value() default "";
  String param2() default "";
}

@Retention(RUNTIME)
@DeepAnnotation("Lorem ipsum dolor sit amet")
@interface MetaAnnotation {
  @AliasFor(annotation = DeepAnnotation.class,
            attribute = "param2")
  String value();
}

public class AliasDemo {
  @MetaAnnotation("3")
  public void m() {
  }
}

@DisplayNameGeneration(TestUtils.ReplaceCamelCase.class)
@ExtensionMethod(value = AnnotatedElementUtils.class, suppressBaseMethods =false)
class AliasDemoTest {

  @Test
  @SneakyThrows
  @DisplayName("Annotation scan works correctly")
  void annotationScanWorksCorrectlyTest() {
    assertThat(AliasDemo.class.getMethod("m")
                   .findMergedAnnotation(DeepAnnotation.class))
        .isNotNull()
        .isInstanceOf(DeepAnnotation.class)
        .extracting(DeepAnnotation::param1, DeepAnnotation::param2)
        .contains("Lorem ipsum dolor sit amet", "3");
  }
}
