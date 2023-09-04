package ru.vlapin.demo.lombokdemo.stable.data;

import lombok.Data;

/**
 * CustomizationDemo.
 */
@Data(staticConstructor = "StaticConstructorDemo")
//@RequiredArgsConstructor(
//  staticName = "StaticConstructorDemo")
public class StaticConstructorDemo {
  int x;
  final String s;
  boolean b;
}
