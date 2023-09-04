package ru.vlapin.demo.lombokdemo.stable.data;

import lombok.*;

import static lombok.AccessLevel.*;

/**
 * DataCustomizationDemo.
 */
@Data
//@Getter
//@ToString
@Setter(PROTECTED)
//@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "x")
public class DataCustomizationDemo {
  int x;
  final String s;
  boolean b;
}
