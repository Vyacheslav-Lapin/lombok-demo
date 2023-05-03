package ru.vlapin.demo.lombokdemo.common.aliasfor;

import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AliasFor;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.*;
import static org.assertj.core.api.Assertions.*;

@Retention(RUNTIME)
public @interface MyDeepAnnotation {
  @AliasFor("value")
  String param1() default "";

  @AliasFor("param1")
  String value() default "";

  String param2() default "";
}

@Retention(RUNTIME)
@MyDeepAnnotation("Lorem ipsum dolor sit amet")
@interface MyAnnotation {

  @AliasFor(annotation = MyDeepAnnotation.class,
            attribute = "param2")
  String value();
}

@SpringBootTest
@ExtensionMethod({
    AnnotatedElementUtils.class,
})
class ScanTest {

  @Test
  @SneakyThrows
  @DisplayName("Annotation scan works correctly")
  void annotationScanWorksCorrectlyTest() {
    assertThat(B.class.getMethod("m").findMergedAnnotation(MyDeepAnnotation.class))
        .isNotNull()
        .isInstanceOf(MyDeepAnnotation.class)
        .extracting(MyDeepAnnotation::param1, MyDeepAnnotation::param2)
        .contains("Lorem ipsum dolor sit amet", "3");
  }
}

class B {
  @MyAnnotation("3")
  public void m() {
  }
}
