package ru.vlapin.demo.lombokdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.*;

@RestController
public class Example1Controller {
  @GetMapping(path = "/testExceptionHandler",
              produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<String> testExceptionHandler(
      @RequestParam(required = false, defaultValue = "false") boolean exception)
      throws BusinessException {

    if (exception)
      throw new BusinessException("BusinessException in testExceptionHandler");
    return ResponseEntity.ok("OK");
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<String> handleException(BusinessException e) {
    return ResponseEntity.internalServerError().body(e.getMessage());
  }
}
