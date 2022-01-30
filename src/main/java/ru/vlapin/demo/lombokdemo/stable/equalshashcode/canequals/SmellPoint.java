package ru.vlapin.demo.lombokdemo.stable.equalshashcode.canequals;

import java.util.Objects;

public class SmellPoint extends Point1 {

  Smell smell;

  public SmellPoint(int x, int y, Smell smell) {
    super(x, y); this.smell = smell; }

  @Override public boolean equals(Object o) {
    return o instanceof Point1 point
               && super.equals(point)
               && (!(point instanceof SmellPoint smellPoint)
                       || Objects.equals(smell, smellPoint.smell));
  }
}

enum Smell { BAD, GOOD}
