package ru.vlapin.demo.lombokdemo.stable.tostring.exclude;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

/**
 * Person2.
 */
@SuppressWarnings({"java:S125", "unused"})
@ToString(of = {"firstName", "lastName"})
@RequiredArgsConstructor
public class Person2 {
  Long id; // исключаем из toString
  String firstName;
  String lastName;
  LocalDate dob; // тоже исключаем

//@Override
//public String toString() {
//  return "Person2(" +
//      "firstName=" + firstName +
//      ", lastName=" + lastName + ")";
//}
}
