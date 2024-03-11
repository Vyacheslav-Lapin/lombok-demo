package ru.vlapin.demo.lombokdemo.experimental.accessors.conf;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.With;
import lombok.experimental.NonFinal;

@SuppressWarnings({"java:S125", "CommentedOutCode"})

@Getter
@Setter
@AllArgsConstructor
public class MakeFinalConfExample {

  @NonFinal int x;
  @With
  String s;

//public final int getX() { return this.x; }

//public final String getS() { return this.s; }

//public final MakeFinalExample setX(int x) {
//  this.x = x; return this;
//}

//public final MakeFinalExample withS(String s) {
//  return this.s == s ? this
//      : new MakeFinalExample(this.x, s);
//}
}
