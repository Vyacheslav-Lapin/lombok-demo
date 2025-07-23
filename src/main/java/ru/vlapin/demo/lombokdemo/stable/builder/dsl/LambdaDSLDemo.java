package ru.vlapin.demo.lombokdemo.stable.builder.dsl;

import java.util.Map;

/**
 * Практическое задание - сделать инициализацию сложного составного класса при помощи подхода Lambda DSL.
 *
 * <p>Пример использования:
 * <pre>{@code
 *   val person = Person(personBuilder -> personBuilder
 *       .fio("Vasya Pupkin")
 *       .address(addressBuilder -> addressBuilder
 *           .street("Bolshevikov prospect")
 *           .apartment(2))
 *       .age(16));
 *
 *   System.out.println("person = " + person); // Person(...)
 * }</pre>
 *
 * @see <a href="https://docs.spring.io/spring-security/reference/migration-7/configuration.html#_use_the_lambda_dsl">Lambda DSL</a>
 */
record Person(
    String fio,
    int age,
    boolean isMarried,
    Address address,
    Map<String, String> contacts) {
}

record Address(String city,
               String street,
               int houseNumber,
               int apartment) {
}

public class LambdaDSLDemo {

  public static void main() {
    Person person = null;
//        Person(personBuilder -> personBuilder
//            .fio("Vasya Pupkin")
//            .address(addressBuilder -> addressBuilder
//                .street("Bolshevikov prospect")
//                .apartment(2))
//            .age(16));

    System.out.println("person = " + person); // Person(...)
  }
}
