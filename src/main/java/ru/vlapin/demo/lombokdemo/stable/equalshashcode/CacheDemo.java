package ru.vlapin.demo.lombokdemo.stable.equalshashcode;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.experimental.NonFinal;

import static lombok.EqualsAndHashCode.CacheStrategy.*;

/**
 * CacheDemo.
 */
@SuppressWarnings({"java:S125", "CommentedOutCode"})
@EqualsAndHashCode(cacheStrategy = LAZY)
@Setter
@AllArgsConstructor
public class CacheDemo {
  @NonFinal int x;
  String s;

//@NonFinal
//private transient int $hashCodeCache;

//@Override
//public int hashCode() {
//  if ($hashCodeCache != 0)
//    return $hashCodeCache;
//
//  int result = 59 * (59 + x) +
//      (s == null ? 43 : s.hashCode());
//
//  return $hashCodeCache = result == 0 ?
//      Integer.MIN_VALUE : result;
//}
}
