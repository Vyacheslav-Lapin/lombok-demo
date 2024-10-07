package ru.vlapin.demo.lombokdemo.controller;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Either;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Singular;
import lombok.val;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.ParameterErrors;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @SuppressWarnings("java:S1190")
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(ConstraintViolationException ex) {
    val errors = ex.getConstraintViolations().stream()
                   .collect(Collectors.toMap(
                       error -> error.getPropertyPath().toString(),
                       ConstraintViolation::getMessage,
                       (_, value) -> value));
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleHandlerMethodValidationException(
      HandlerMethodValidationException ex,
      HttpHeaders __, HttpStatusCode ___, WebRequest ____) {

    Function<ParameterValidationResult, Either<Stream<MessageSourceResolvable>, Stream<FieldError>>> mapper =
        parameterValidationResult -> parameterValidationResult instanceof ParameterErrors parameterErrors ?
            Either.right(parameterErrors.getFieldErrors().stream())
            : Either.left(parameterValidationResult.getResolvableErrors().stream());

    val errors = ex.getAllValidationResults().stream()
                   .flatMap(parameterValidationResult ->
                       mapper.apply(parameterValidationResult)
                             .map(fieldErrors -> fieldErrors.map(fieldError ->
                                 Tuple.of(fieldError.getField(), fieldError.getDefaultMessage())))
                             .getOrElseGet(messageSourceResolvables -> messageSourceResolvables.map(messageSourceResolvable ->
                                 Tuple.of(parameterValidationResult.getMethodParameter().getParameterName(),
                                     messageSourceResolvable.getDefaultMessage()))))
                   .collect(Collectors.toMap(Tuple2::_1, Tuple2::_2));

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                         .body(ErrorDetails.builder()
                                           .message("Validation failed")
                                           .details(errors).build());
  }

  @Builder
  private record ErrorDetails(
      String message,
      @Singular Map<String, String> details) {

  }
}
