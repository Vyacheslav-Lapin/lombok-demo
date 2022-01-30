package ru.vlapin.demo.lombokdemo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
//@AllArgsConstructor
@Getter(AccessLevel.PACKAGE)
@Setter
public class Foo {
  String s;
  boolean b;
}
