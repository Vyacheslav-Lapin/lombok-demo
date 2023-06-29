package ru.vlapin.demo.lombokdemo.stable.setter;

import lombok.Getter;
import lombok.Setter;

/**
 * SelectiveReplacementExample.
 */
@Setter
@Getter
public class SelectiveReplacementExample {

  int x;
  //@Setter
  int y;
  //@Setter
  int z;

  // А если полю нужен свой,
  // специальный setter?..
  public void setX(String x) {
    this.x = Integer.parseInt(x);
  }
}
