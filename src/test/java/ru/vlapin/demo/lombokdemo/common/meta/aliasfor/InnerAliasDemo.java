package ru.vlapin.demo.lombokdemo.common.meta.aliasfor;

import static java.lang.annotation.RetentionPolicy.*;
import static org.assertj.core.api.Assertions.*;

import java.lang.annotation.Retention;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.core.annotation.AliasFor;
import org.springframework.core.annotation.AnnotatedElementUtils;
import ru.vlapin.demo.lombokdemo.common.TestUtils;

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

@ExtensionMethod(value = AnnotatedElementUtils.class,
                 suppressBaseMethods = false)
@DisplayNameGeneration(TestUtils.ReplaceCamelCase.class)
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
        .extracting(InnerAlias::x, InnerAlias::s)
        .contains(5, "lor");
  }
}
