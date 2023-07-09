package ru.vlapin.demo.lombokdemo.experimental.getter.annotating;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * SecondAnnotation.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SecondAnnotation {
  String value();
}
