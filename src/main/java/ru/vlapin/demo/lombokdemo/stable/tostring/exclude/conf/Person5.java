package ru.vlapin.demo.lombokdemo.stable.tostring.exclude.conf;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

/**
 * Person5.
 */
@SuppressWarnings({"java:S125", "unused"})
@ToString
@RequiredArgsConstructor
public class Person5 {
  Long id; // исключаем из toString
  @ToString.Include String firstName;
  @ToString.Include String lastName;
  LocalDate dob; // тоже исключаем

//@Override
//public String toString() {
//  return "Person5(" +
//      "firstName=" + firstName +
//      ", lastName=" + lastName + ")";
//}
}
