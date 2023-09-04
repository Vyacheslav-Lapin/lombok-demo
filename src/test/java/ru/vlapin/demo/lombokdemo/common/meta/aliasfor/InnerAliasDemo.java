package ru.vlapin.demo.lombokdemo.common.meta.aliasfor;

import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.annotation.AliasFor;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.*;
import static org.assertj.core.api.Assertions.*;

@Retention(RUNTIME)
@interface InnerAlias {
  @AliasFor("value") int x() default 0;
  @AliasFor("x") int value() default 0;
  String s() default "lor";
}

public class InnerAliasDemo {
  @InnerAlias(5)
  public void method() {
  }
}

@ExtensionMethod({
    AnnotatedElementUtils.class,
})
class InnerAliasDemoTest {

  @Test
  @SneakyThrows
  @DisplayName("Inner params aliasing works correctly")
  void innerParamsAliasingWorksCorrectlyTest() {
    // given
    val innerAlias = InnerAliasDemo.class
        .getMethod("method")
        .findMergedAnnotation(InnerAlias.class);

    // when
    assertThat(innerAlias)
        // then
        .isNotNull()
        .extracting(InnerAlias::x,
                    InnerAlias::s)
        .contains(5, "lor");
  }
}
