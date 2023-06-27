package ru.vlapin.demo.lombokdemo.stable.setter;

import lombok.Getter;
import lombok.Setter;

/**
 * SelectiveReplacementExample.
 */
@Setter
@Getter
public class SelectiveReplacementExample {

  private int x;
  //@Setter
  private int y;
  //@Setter
  private int z;

  // А если полю нужен свой,
  // специальный setter?..
  public void setX(String x) {
    this.x = Integer.parseInt(x);
  }
}
