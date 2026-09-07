package ru.vlapin.demo.lombokdemo.model;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import lombok.val;

@Value
@Jacksonized
@Builder(toBuilder = true)
public class Dog {
  String name;
  @Default int age = 5;
  boolean isGood;

  static void main() {
    Dog dog = Dog.builder()
                 .name("Rex")
//                 .age(10)
                 .isGood(true)
                 .build();

    System.out.println(dog); // age=5...

//    val modifiedDog = dog.withAge(12)
//                         .withGood(false)
//                         .withName("Бобик");

    val updatedDog = dog.toBuilder()
                   .age(11)
                   .isGood(false)
                   .name("Бобик")
                   .build();
//
//    Dog2.builder()
//        .lengthOfSherst(10)
//        .age(56)
//        .lengthOfSherst(15)
//        .isGood(true)
//        .build();
  }
}

//@SuperBuilder
//class Dog2 extends Dog {
//  int lengthOfSherst;
//}

//@Builder
//public record Dog(String name,
//                  @Default int age, // = 5
//                  boolean isGood) {
//
//  static void main(String... __) {
//    Dog dog = Dog.builder()
//                 .name("Rex")
// //                .age(10)
//                 .isGood(true)
//                 .build();
//  }
//}
