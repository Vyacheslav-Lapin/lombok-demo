package ru.vlapin.demo.lombokdemo.experimental.unobtrusive;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.NonFinal;

@Getter
@Setter
@Accessors(chain = true)
public class UnobtrusiveDemo1 {
  @NonFinal int x;
  @NonFinal boolean b;

  public String getX() { return Integer.toString(x); }
//public UnobtrusiveDemo1 setX(int x) {
//  this.x = x; return this; }

//public boolean isB() { return this.b; }
  public UnobtrusiveDemo1 setB(String b) {
    this.b = Boolean.parseBoolean(b); return this; }
}
