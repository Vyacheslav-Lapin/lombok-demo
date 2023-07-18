package ru.vlapin.demo.lombokdemo.stable.equalshashcode.exclude;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.NonFinal;

import java.time.LocalDate;

@SuppressWarnings("java:S125")
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
public class Person4 {
  @EqualsAndHashCode.Exclude @NonFinal Long id;
  @NonFinal String firstName;
  @NonFinal String lastName;
  @EqualsAndHashCode.Exclude @NonFinal LocalDate dob;

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
