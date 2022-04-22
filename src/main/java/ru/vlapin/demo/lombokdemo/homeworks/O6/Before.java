package ru.vlapin.demo.lombokdemo.homeworks.O6;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Retention(RUNTIME)
@Target({METHOD, ANNOTATION_TYPE})
public @interface Before {
}
