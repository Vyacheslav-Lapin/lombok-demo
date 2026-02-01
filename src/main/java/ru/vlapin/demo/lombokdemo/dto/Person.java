package ru.vlapin.demo.lombokdemo.dto;

import java.util.List;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Singular;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Person {
  String firstName;
  @Default String lastName = "Мирошниченко";
  int age;
  @Singular List<String> contacts;
}
