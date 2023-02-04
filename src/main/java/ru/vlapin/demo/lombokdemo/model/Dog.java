package ru.vlapin.demo.lombokdemo.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dog {
  String name;
  LocalDate dob;
}
