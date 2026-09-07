package ru.vlapin.demo.lombokdemo.experimental.checker.framework;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class User {
  @NonNull String username; // обязательное поле
  @NonNull String email;    // обязательное поле
  String firstName;         // опциональное
  int age;                  // опциональное (примитив)
}
