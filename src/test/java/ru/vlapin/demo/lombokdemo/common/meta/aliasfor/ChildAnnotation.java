package ru.vlapin.demo.lombokdemo.common.meta.aliasfor;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.*;

@InnerAlias
@Retention(RUNTIME)
public @interface ChildAnnotation {

  @AliasFor(
      annotation = InnerAlias.class)
  int value() default 0;
}
