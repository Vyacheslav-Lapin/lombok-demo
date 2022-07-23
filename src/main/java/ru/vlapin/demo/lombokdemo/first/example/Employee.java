package ru.vlapin.demo.lombokdemo.first.example;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
class Employee extends Person {
  String companyName;
  String position;
}
