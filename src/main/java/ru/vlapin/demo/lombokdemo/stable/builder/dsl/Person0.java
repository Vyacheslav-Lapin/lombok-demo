package ru.vlapin.demo.lombokdemo.stable.builder.dsl;

import lombok.Builder;

@Builder
public record Person0 (
  String firstName,
  String lastName,
  int age,
  boolean isMarried,
  boolean isMale) {
}

//import static lombok.AccessLevel.*;
//
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//import lombok.experimental.Accessors;
//import lombok.experimental.FieldDefaults;
//
////@Builder
//public record Person0(String firstName,
//                      String lastName,
//                      int age,
//                      boolean isMarried,
//                      boolean isMale) {
//
//  @Setter
//  @ToString
//  @Accessors(fluent = true)
//  @FieldDefaults(level = PRIVATE)
//  @NoArgsConstructor(access = PACKAGE)
//  public static class Person0Builder {
//    String firstName;
//    String lastName;
//    int age;
//    boolean isMarried;
//    boolean isMale;
//
//    public Person0 build() {
//      return new Person0(firstName, lastName, age, isMarried,isMale);
//    }
//  }
//
//  public static Person0.Person0Builder builder() {
//    return new Person0.Person0Builder();
//  }
//}
