package ru.vlapin.demo.lombokdemo.common.aliasfor;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target(TYPE)
@Retention(RUNTIME)
public @interface AnnotaionBase {
  String value() default "";
}
