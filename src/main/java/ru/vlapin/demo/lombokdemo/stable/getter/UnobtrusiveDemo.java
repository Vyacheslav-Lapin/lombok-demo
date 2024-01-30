package ru.vlapin.demo.lombokdemo.stable.getter;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UnobtrusiveDemo {
  int x;
  String s;
  boolean b;

  public String getX() {
    return Integer.toString(x);
  }

  public UnobtrusiveDemo setB(String b) {
    this.b = Boolean.parseBoolean(b);
    return this;
  }
}
