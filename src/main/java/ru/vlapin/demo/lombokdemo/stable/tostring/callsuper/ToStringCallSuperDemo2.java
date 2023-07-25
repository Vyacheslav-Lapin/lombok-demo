package ru.vlapin.demo.lombokdemo.stable.tostring.callsuper;

import lombok.ToString;
import ru.vlapin.demo.lombokdemo.stable.tostring.ToStringDemo;

/**
 * ToStringCallSuperDemo1.
 */
@SuppressWarnings("java:S125")
@ToString
public class ToStringCallSuperDemo2
    extends ToStringDemo {

  int x1;
  String s1;

  public ToStringCallSuperDemo2(int x,
                                String s,
                                int x1,
                                String s1) {
    super(x, s);
    this.x1 = x1;
    this.s1 = s1;
  }

//@Override
//public String toString() {
//  return "ToStringCallSuperDemo1("
//      + "super=" + super.toString()
//      + ", x1=" + this.x1
//      + ", s1=" + this.s1 + ")";
//}
}
