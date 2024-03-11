package ru.vlapin.demo.lombokdemo.stable.with;

import lombok.Value;
import lombok.With;
import lombok.experimental.Accessors;

@SuppressWarnings({
    "java:S125",
    "CommentedOutCode",
    "ClassCanBeRecord",
})

@With
@Value
@Accessors(makeFinal = true)
public class MakeFinalParamDemo {
  int x;

//  public final int getX() {
//      return this.x;
//    }
//
//  public final MakeFinalParamDemo withX(final int x) {
//      return this.x == x ? this : new MakeFinalParamDemo(x);
//    }
}
