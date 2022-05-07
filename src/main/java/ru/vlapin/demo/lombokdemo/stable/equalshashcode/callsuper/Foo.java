package ru.vlapin.demo.lombokdemo.stable.equalshashcode.callsuper;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor(force = true)
class Foo {
  private int x;
  private String s;
}

@EqualsAndHashCode//(callSuper = true)
@NoArgsConstructor(force = true)
class Bar extends Foo {
  private int y;
  private String s1;
}

@EqualsAndHashCode//(callSuper = true)
@NoArgsConstructor(force = true)
class Baz extends Bar {
  private int z;
  private String s2;
}

@EqualsAndHashCode//(callSuper = true)
@NoArgsConstructor(force = true)
class Qux extends Baz {
  private int a;
  private String s3;
}
