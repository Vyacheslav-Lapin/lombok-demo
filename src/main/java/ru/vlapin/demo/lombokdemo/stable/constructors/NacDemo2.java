package ru.vlapin.demo.lombokdemo.stable.constructors;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.NonFinal;

/**
 * NacDemo2.
 */
@SuppressWarnings({"java:S125", "CommentedOutCode", "unused"})
@NoArgsConstructor
@AllArgsConstructor
public class NacDemo2 {
  @NonFinal int x;
  @NonFinal String s;
  @NonFinal boolean z;

//public NacDemo2() {
//}
}
