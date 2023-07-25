package ru.vlapin.demo.lombokdemo.stable.tostring.exclude;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

/**
 * Person4.
 */
@SuppressWarnings({"java:S125", "unused"})
@ToString(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class Person4 {
  Long id; // исключаем из toString
  @ToString.Include String firstName;
  @ToString.Include String lastName;
  LocalDate dob; // тоже исключаем

//@Override
//public String toString() {
//  return "Person4(" +
//      "firstName=" + firstName +
//      ", lastName=" + lastName + ")";
//}
}
