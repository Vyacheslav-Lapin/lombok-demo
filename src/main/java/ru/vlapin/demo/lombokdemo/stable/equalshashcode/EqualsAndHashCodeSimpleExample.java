package ru.vlapin.demo.lombokdemo.stable.equalshashcode;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

// Решение:
@EqualsAndHashCode
@AllArgsConstructor
public class EqualsAndHashCodeSimpleExample {
  private int x;
  private String s;

  // Как писать/генерировать методы equals и hashCode?
}
