package ru.vlapin.demo.lombokdemo.stable.value;

import lombok.AllArgsConstructor;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

//@SuppressWarnings("Lombok")

@SuppressWarnings("unused")

@Target(TYPE)
@Retention(SOURCE)

//@Getter
//@ToString
//@EqualsAndHashCode
//@AllArgsConstructor
//@FieldDefaults(level = PRIVATE,
//               makeFinal = true)
public @interface Value {

  @AliasFor(annotation = AllArgsConstructor.class)
  String staticName() default "";
}
