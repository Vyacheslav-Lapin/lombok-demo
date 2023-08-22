package ru.vlapin.demo.lombokdemo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * SecondAnnotation.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SecondAnnotation {
  String value();
}
