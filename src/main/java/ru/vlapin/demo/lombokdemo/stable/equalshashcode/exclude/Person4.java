package ru.vlapin.demo.lombokdemo.stable.equalshashcode.exclude;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@SuppressWarnings({"java:S125", "CommentedOutCode", "ClassCanBeRecord"})
@EqualsAndHashCode
@Getter
@RequiredArgsConstructor
public class Person4 {
  @EqualsAndHashCode.Exclude Long id; // исключаем
  String firstName;
  String lastName;
  @EqualsAndHashCode.Exclude LocalDate dob; // тоже

//public boolean equals(final Object o) {
//  return o == this
//      || o instanceof Person3 other
//      && other.canEqual(this)
//      && (Objects.equals(firstName, other.firstName))
//      && (Objects.equals(lastName, other.lastName));}

//protected boolean canEqual(Object other) {
//  return other instanceof Person3;}

//public int hashCode() {
//  return 59 * (59
//      + (firstName == null ? 43 : firstName.hashCode()))
//      + (lastName == null ? 43 : lastName.hashCode());}
}
