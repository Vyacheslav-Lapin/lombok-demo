package ru.vlapin.demo.lombokdemo.stable.nullability.record;

import lombok.NonNull;

@SuppressWarnings({"java:S125", "CommentedOutCode"})

public record NonNullRecordDemo(
    @NonNull
    Integer x) {

//  public NonNullRecordDemo {
//      java.util.Objects.requireNonNull(
//          x,
//          "x is marked non-null but is null");
//  }
}
