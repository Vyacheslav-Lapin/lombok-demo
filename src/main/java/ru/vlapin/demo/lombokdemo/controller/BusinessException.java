package ru.vlapin.demo.lombokdemo.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Generated;

/**
 * К сожалению, аннотация пока не поддерживается IDEA
 * Lombok Plugin'ом. Так что я её Delombok'нул - иначе в
 * IDEA всё было бы красным. Проголосовать за поддержку
 * этой аннотации в IDEA Plugin'е можно
 * {@link <a href="https://github.com/mplushnikov/lombok-intellij-plugin/issues/1076">здесь</a>}
 *
 * @see <a href="https://projectlombok.org/features/experimental/StandardException">documentation</a>
 */
//@StandardException
public class BusinessException extends RuntimeException {

  @Generated
  @SuppressWarnings("all")
  @SuppressFBWarnings(justification = "generated code")
  public BusinessException() {
    this(null, null);
  }

  @Generated
  @SuppressWarnings("all")
  @SuppressFBWarnings(justification = "generated code")
  public BusinessException(String message) {
    this(message, null);
  }

  @Generated
  @SuppressWarnings("all")
  @SuppressFBWarnings(justification = "generated code")
  public BusinessException(Throwable cause) {
    this(cause != null ? cause.getMessage() : null, cause);
  }

  @Generated
  @SuppressWarnings("all")
  @SuppressFBWarnings(justification = "generated code")
  public BusinessException(String message, Throwable cause) {
    super(message);
    if (cause != null) super.initCause(cause);
  }
}
