package ru.vlapin.demo.lombokdemo.stable.data.extraprivate;

import lombok.Data;

/**
 * ExtraPrivateDemo.
 */
@Data
//@RequiredArgsConstructor
//@NoArgsConstructor(access = PRIVATE,
//                   force = true)
public class ExtraPrivateDemo {
  int x;
  final String s;
  boolean b;
}
