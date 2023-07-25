package ru.vlapin.demo.lombokdemo.stable.tostring.exclude;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

/**
 * Person1.
 */
@SuppressWarnings({"java:S125", "unused"})
@ToString(exclude = {"id", "dob"})
@RequiredArgsConstructor
public class Person1 {
  Long id; // исключаем из toString
  String firstName;
  String lastName;
  LocalDate dob; // тоже исключаем

//@Override
//public String toString() {
//  return "Person1(" +
//      "firstName=" + firstName +
//      ", lastName=" + lastName + ")";
//}
}
