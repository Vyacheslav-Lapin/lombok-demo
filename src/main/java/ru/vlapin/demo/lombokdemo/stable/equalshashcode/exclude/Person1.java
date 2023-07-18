package ru.vlapin.demo.lombokdemo.stable.equalshashcode.exclude;

import lombok.*;
import lombok.experimental.NonFinal;

import java.time.LocalDate;

@SuppressWarnings("java:S125")
@EqualsAndHashCode(exclude = {"id", "dob"})
@Getter
@Setter
@AllArgsConstructor
public class Person1 {
  @NonFinal Long id; // исключаем поле из equals и hashcode
  @NonFinal String firstName;
  @NonFinal String lastName;
  @NonFinal LocalDate dob; // тоже исключаем

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
