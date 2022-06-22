package ru.vlapin.demo.lombokdemo.stable.builder;

import lombok.Builder;

@Builder(toBuilder = true)
@SuppressWarnings({"java:S125", "CommentedOutCode"})
public class ToBuilderWithObtainViaDemo {
  @Builder.ObtainVia(method = "s")
  String s;

  @SuppressWarnings("unused")
  private String s() {
    return "%s copy".formatted(s);
  }

//  public ToBuilderWithObtainViaDemoBuilder toBuilder() {
//    return new ToBuilderWithObtainViaDemoBuilder()
//               .s(s());
//  }
}
