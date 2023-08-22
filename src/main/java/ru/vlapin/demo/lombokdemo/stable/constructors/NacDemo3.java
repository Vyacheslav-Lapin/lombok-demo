package ru.vlapin.demo.lombokdemo.stable.constructors;

import lombok.NoArgsConstructor;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.jetbrains.annotations.Nullable;

/**
 * NacDemo2.
 */
@SuppressWarnings({"java:S125", "CommentedOutCode", "unused"})
@DefaultQualifier(Nullable.class)
@NoArgsConstructor(force = true)
public class NacDemo3 {
  int x;
  String s;
  boolean z;

//public NacDemo3() {
//  this.x = 0;
//  this.s = null;
//  this.z = false;
//}
}
