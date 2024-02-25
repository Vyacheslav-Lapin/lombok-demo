package ru.vlapin.demo.lombokdemo.stable.data;

import static lombok.AccessLevel.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

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
