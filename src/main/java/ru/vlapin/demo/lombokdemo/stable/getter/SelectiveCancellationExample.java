package ru.vlapin.demo.lombokdemo.stable.getter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * SelectiveCancellationExample.
 */
@Getter
@Setter
public class SelectiveCancellationExample {
//@Getter
//@Setter
  private int x;
  
//@Getter
//@Setter
  private int y;

  // А если НЕКОТОРЫМ полям не
  // нужны getter’ы и setter’ы?..
  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  private int z;
}
