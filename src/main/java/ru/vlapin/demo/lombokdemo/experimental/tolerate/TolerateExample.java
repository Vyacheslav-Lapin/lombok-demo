package ru.vlapin.demo.lombokdemo.experimental.tolerate;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

/**
 * TolerateExample.
 */
@Setter
@Getter
public class TolerateExample {

  private int x;

  @Tolerate
  public void setX(String x) {
    setX(Integer.parseInt(x));
  }

//public void setX(int x) {
//  this.x = x;
//}
}
