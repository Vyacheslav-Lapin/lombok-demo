package ru.vlapin.demo.lombokdemo.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vlapin.demo.lombokdemo.validation.CardNumber;

public class ValidationExampleController {
  @PostMapping("/card")
  ResponseEntity<String> checkCardNumber(@RequestParam @Valid @CardNumber String cardNumber) {
    return ResponseEntity.ok()
                         .body(cardNumber);
  }
}
