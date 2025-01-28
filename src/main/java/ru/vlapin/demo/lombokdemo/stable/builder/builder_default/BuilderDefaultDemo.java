package ru.vlapin.demo.lombokdemo.stable.builder.builder_default;

import lombok.Builder;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(makeFinal = true)
//@AllArgsConstructor(access = PACKAGE)
@SuppressWarnings({"java:S125", "CommentedOutCode"})
public class BuilderDefaultDemo {

  @Builder.Default int x = 7; // int x;
//  int x; // @Builder.Default int x = 7;

//  private static int $default$x() { return 7; }

//  @ToString
//  @FieldDefaults(level = PRIVATE)
//  @NoArgsConstructor(access = PACKAGE)
//  public static class BuilderDefaultDemoBuilder {
//    @ToString.Exclude boolean x$set;
//    int x$value;

//    public BuilderDefaultDemoBuilder x(final int x) {
//      x$value = x;
//      x$set = true;
//      return this;
//    }

//    public BuilderDefaultDemo build() {
//      return new BuilderDefaultDemo(!x$set ? $default$x() : this.x$value);
//    }
//  }

//  public static BuilderDefaultDemoBuilder builder() {
//    return new BuilderDefaultDemoBuilder();
//  }
}
