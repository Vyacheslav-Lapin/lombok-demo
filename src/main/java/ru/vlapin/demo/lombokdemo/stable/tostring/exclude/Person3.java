package ru.vlapin.demo.lombokdemo.stable.tostring.exclude;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

/**
 * Person3.
 */
@SuppressWarnings({"java:S125", "unused"})
@ToString
@RequiredArgsConstructor
public class Person3 {
  @ToString.Exclude Long id; // исключаем
  String firstName;
  String lastName;
  @ToString.Exclude LocalDate dob; // тоже

//@Override
//public String toString() {
//  return "Person3(" +
//      "firstName=" + firstName +
//      ", lastName=" + lastName + ")";
//}
}
