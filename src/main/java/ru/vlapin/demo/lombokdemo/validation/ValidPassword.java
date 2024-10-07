package ru.vlapin.demo.lombokdemo.validation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import lombok.experimental.NonFinal;
import org.checkerframework.checker.nullness.qual.Nullable;

@Retention(RUNTIME)
@Target({FIELD, METHOD, PARAMETER})
@Constraint(validatedBy = PasswordValidator.class)
public @interface ValidPassword {
  String message() default "Invalid password";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
  int minLength() default 8;
  String specialChars() default "!@#$%^&*()";
}

@SuppressWarnings("NotNullFieldNotInitialized")
class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
  @NonFinal int minLength;
  @NonFinal String specialChars;

  @Override
  public void initialize(ValidPassword constraintAnnotation) {
    this.minLength = constraintAnnotation.minLength();
    this.specialChars = constraintAnnotation.specialChars();
  }

  @Override
  public boolean isValid(@Nullable String password,
                         ConstraintValidatorContext context) {
    if (password == null) return false;

    boolean hasUpperCase = !password.equals(password.toLowerCase());
    boolean hasLowerCase = !password.equals(password.toUpperCase());
    boolean hasDigit = password.chars().anyMatch(Character::isDigit);
    boolean hasSpecialChar = password.chars()
                                     .anyMatch(ch -> specialChars.indexOf(ch) >= 0);
    boolean isLongEnough = password.length() >= minLength;

    return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar && isLongEnough;
  }
}
