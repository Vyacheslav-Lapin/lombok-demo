package ru.vlapin.demo.lombokdemo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Соответствует ли строка формату номера кредитной карты?
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = "(\\d{4}-){3}\\d{4}$", message = "Invalid credit card number")
@Constraint(validatedBy = {})
public @interface CardNumber {
  String message() default "Invalid credit card number";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
