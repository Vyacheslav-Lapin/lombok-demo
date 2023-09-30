package ru.vlapin.demo.lombokdemo.stable.data;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;


/**
 * Data.
 */
//@SuppressWarnings("Lombok")

@SuppressWarnings("unused")

@Target(TYPE)
@Retention(SOURCE)

//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode
//@RequiredArgsConstructor
public @interface Data {
  @AliasFor(annotation = RequiredArgsConstructor.class)
  String staticName() default "";
}
