package ru.vlapin.demo.lombokdemo.first.example;

import java.time.LocalDate;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@SuperBuilder(toBuilder = true)
public class Person {
  String firstName;
  String lastName;
  LocalDate dateOfBirth;
  String address;
  String telephone;
}
