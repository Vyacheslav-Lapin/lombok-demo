package ru.vlapin.demo.lombokdemo.experimental.checker.framework;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserTest {

  @Test
  void test() {
    // Правильное использование — должно проходить проверку
    User user1 = User.builder()
                     .username("john_doe")
                     .email("john@example.com")
                     .firstName("John")
                     .age(30)
                     .build();

    // Неправильное использование — Checker Framework должен выдать ошибку
    // (отсутствует username или email)
//    User user2 = User.builder()
//                     .email("test@example.com")   // ошибка: не вызван username(...)
//                     .build();
  }
}
