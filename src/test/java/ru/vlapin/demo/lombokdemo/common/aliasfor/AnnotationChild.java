package ru.vlapin.demo.lombokdemo.common.aliasfor;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target(TYPE)
@AnnotaionBase
@Retention(RUNTIME)
public @interface AnnotationChild {

  @AliasFor(annotation = AnnotaionBase.class, attribute = "value")
  String extendValue() default "";
}
