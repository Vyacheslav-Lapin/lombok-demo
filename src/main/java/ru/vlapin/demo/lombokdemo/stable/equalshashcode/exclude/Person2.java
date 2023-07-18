package ru.vlapin.demo.lombokdemo.stable.equalshashcode.exclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.NonFinal;

import java.time.LocalDate;

@SuppressWarnings("java:S125")
@EqualsAndHashCode(of = {"firstName", "lastName"})
@Data
@AllArgsConstructor
public class Person2 {
  @NonFinal Long id; // исключаем поле из equals и hashcode
  @NonFinal String firstName;
  @NonFinal String lastName;
  @NonFinal LocalDate dob; // тоже исключаем

//public boolean equals(final Object o) {
//  return o == this
//      || o instanceof Person2 other
//      && other.canEqual(this)
//      && (Objects.equals(firstName, other.firstName))
//      && (Objects.equals(lastName, other.lastName));}

//protected boolean canEqual(Object other) {
//  return other instanceof Person2;}

//public int hashCode() {
//  return 59 * (59
//      + (firstName == null ? 43 : firstName.hashCode()))
//      + (lastName == null ? 43 : lastName.hashCode());}
}
