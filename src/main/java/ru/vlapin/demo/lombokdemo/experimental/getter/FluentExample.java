package ru.vlapin.demo.lombokdemo.experimental.getter;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * FluentExample.
 */
// Зачем писать префиксы "get" и...
// ..."set"? "Конфликта имён" нет - есть же
// параметр у setter'а и отсутствие параметра у
// getter'а, это простой overloading!
@Accessors(fluent = true)
@Getter
@Setter
@SuppressWarnings("java:S125")
public class FluentExample {
//@Accessors(chain = false)
  String s;
  @Accessors(chain = false)
  int x;

//public FluentExample s(final String s) {
//  this.s = s;
//  return this;
//}

//public void x(final int x) { this.x = x; }

//public String s() { return this.s; }

//public int x() { return this.x; }
}
