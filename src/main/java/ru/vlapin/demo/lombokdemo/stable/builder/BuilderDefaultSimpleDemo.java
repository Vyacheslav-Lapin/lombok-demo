package ru.vlapin.demo.lombokdemo.stable.builder;

import lombok.Builder;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(makeFinal = true)
//@AllArgsConstructor(access = PACKAGE)
@SuppressWarnings({"java:S125", "CommentedOutCode"})
public class BuilderDefaultSimpleDemo {

  @Builder.Default int x = 7; // int x;
//  int x; // @Builder.Default int x = 7;

//  private static int $default$x() { return 7; }

//  @ToString
//  @FieldDefaults(level = PRIVATE)
//  @NoArgsConstructor(access = PACKAGE)
//  public static class BuilderDefaultSimpleDemoBuilder {
//    @ToString.Exclude boolean x$set;
//    int x$value;

//    public BuilderDefaultSimpleDemoBuilder x(final int x) {
//      x$value = x;
//      x$set = true;
//      return this;
//    }

//    public BuilderDefaultSimpleDemo build() {
//      return new BuilderDefaultSimpleDemo(!x$set ? $default$x() : this.x$value);
//    }
//  }

//  public static BuilderDefaultSimpleDemoBuilder builder() {
//    return new BuilderDefaultSimpleDemoBuilder();
//  }
}
