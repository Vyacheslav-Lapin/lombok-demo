package ru.vlapin.demo.lombokdemo.experimental.unobtrusive;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.NonFinal;
import lombok.experimental.Tolerate;

@SuppressWarnings("java:S125")
@Setter
@Getter
@Accessors(chain = true)
public class UnobtrusiveDemo2 {
  @NonFinal boolean b;

  @Tolerate
  public UnobtrusiveDemo2 setB(String b) {
    this.b = Boolean.parseBoolean(b);
    return this;
  }

//public UnobtrusiveDemo2 setB(boolean b) {
//  this.b = b;
//  return this;
//}
}
