package ru.vlapin.demo.lombokdemo.stable.builder.dsl;

import static ru.vlapin.demo.lombokdemo.stable.builder.dsl.Address.*;
import static ru.vlapin.demo.lombokdemo.stable.builder.dsl.Person.*;

import java.util.Map;
import java.util.function.UnaryOperator;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import lombok.experimental.Tolerate;
import lombok.val;

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
 *       .contact("tel", "222-33-22")
 *       .contact("email", "vasya@pupkin.ru")
 *       .age(16));
 *
 *   System.out.println("person = " + person); // Person(...)
 * }</pre>
 *
 * @see <a href="https://docs.spring.io/spring-security/reference/migration-7/configuration.html#_use_the_lambda_dsl">Lambda DSL</a>
 */
@SuppressWarnings({"MethodNameSameAsClassName", "unused"})
@Builder(builderMethodName = "Person")
@Value class Person {
  @Builder.Default String fio = "John Doe";
  @Builder.Default int age = 18;
  @Builder.Default boolean isMarried = false;
  @Builder.Default Address address = Address();
  @Singular Map<String, String> contacts;

  public static Person Person(UnaryOperator<PersonBuilder> builderCustomizer) {
    return builderCustomizer.apply(new PersonBuilder()).build();
  }

  public static Person Person() {
    return Person(UnaryOperator.identity());
  }

  public static class PersonBuilder {
    @Tolerate
    public PersonBuilder address(UnaryOperator<AddressBuilder> builderCustomizer) {
      address$value = Address(builderCustomizer);
      address$set = true;
      return this;
    }
  }
}

@SuppressWarnings("MethodNameSameAsClassName")
@Builder(builderMethodName = "Address")
@Value class Address {
  @Builder.Default String city = "Moscow";
  @Builder.Default String street = "Lenina";
  @Builder.Default int houseNumber = 1;
  @Builder.Default int apartment = 1;

  public static Address Address(UnaryOperator<AddressBuilder> builderCustomizer) {
    return builderCustomizer.apply(new AddressBuilder()).build();
  }

  public static Address Address() {
    return Address(UnaryOperator.identity());
  }
}

public class LambdaDSLDemo {
  public static void main() {
    val person = Person(personBuilder -> personBuilder
            .fio("Vasya Pupkin")
            .address(addressBuilder -> addressBuilder
                .street("Bolshevikov prospect")
                .apartment(2))
            .contact("tel", "222-33-22")
            .contact("email", "vasya@pupkin.ru")
            .age(16));

    System.out.println("person = " + person); // Person(...)
  }
}
