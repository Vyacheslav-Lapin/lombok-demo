package ru.vlapin.demo.lombokdemo.stable.builder.dsl;

import java.util.Map;
import lombok.Builder;
import lombok.Singular;

/**
 * Стартовая точка - наш builder ничем не отличается от стандартного, разве что метод назван иначе — не «builder», а «of».
 *
 * <p>Пример использования:
 * <pre>{@code
 *   var ldd = LambdaDslDemo0.of()
 *                           .fio("lorem")
 *                           .age(1)
 *                           .inner(Inner.of()
 *                                       .y(2)
 *                                       .street("ipsum")
 *                                       .prop("prop1", "a")
 *                                       .prop("prop2", "isMarried").build())
 *                           .isMarried(true).build();
 * }</pre>
 *
 * @param fio Фамилия Имя и Отчество
 * @param age возраст (полных лет)
 * @param isMarried женат/за мужем
 * @param address составной параметр
 * @param contacts
 */
@Builder(builderMethodName = "of")
public record Person3(
    String fio,
    int age,
    boolean isMarried,
    Address address,
    @Singular Map<String, String> contacts) {

  @Builder(builderMethodName = "of")
  public record Address(
      String city,
      String street,
      int houseNumber,
      int apartment) {
  }
}
