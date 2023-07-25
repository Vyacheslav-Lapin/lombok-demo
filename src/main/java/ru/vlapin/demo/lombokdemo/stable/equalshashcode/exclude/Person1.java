package ru.vlapin.demo.lombokdemo.stable.equalshashcode.exclude;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@SuppressWarnings({"java:S125", "ClassCanBeRecord", "CommentedOutCode"})
@EqualsAndHashCode(exclude = {"id", "dob"})
@Getter
@RequiredArgsConstructor
public class Person1 {
  Long id; // исключаем поле из equals и hashcode
  String firstName;
  String lastName;
  LocalDate dob; // тоже исключаем

//public boolean equals(final Object o) {
//  return o == this
//      || o instanceof Person1 other
//      && other.canEqual(this)
//      && (Objects.equals(firstName, other.firstName))
//      && (Objects.equals(lastName, other.lastName));}

//protected boolean canEqual(Object other) {
//  return other instanceof Person1;}

//public int hashCode() {
//  return 59 * (59
//      + (firstName == null ? 43 : firstName.hashCode()))
//      + (lastName == null ? 43 : lastName.hashCode());}
}
