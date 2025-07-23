package ru.vlapin.demo.lombokdemo.stable.builder.dsl;

import java.util.Map;
import lombok.Builder;
import lombok.Singular;

@Builder(builderMethodName = "of")
public record LambdaDslDemo1(
    int x,
    String s,
    boolean b,
    Inner inner) {

//  @SuppressWarnings("LombokBuilder")
//  public static LambdaDslDemo of(LambdaDslDemoBuilder lambdaDslDemoBuilder,
//                                 UnaryOperator<LambdaDslDemoBuilder> initer) {
//    return initer.apply(lambdaDslDemoBuilder).build();
//  }
//
//  public static LambdaDslDemo of(UnaryOperator<LambdaDslDemoBuilder> initer) {
//    return of(new LambdaDslDemoBuilder(), initer);
//  }

  @Builder(builderMethodName = "of")
  public record Inner(
      int y,
      String z,
      @Singular Map<String, String> props) {

//    public static class InnerBuilder {
//
//      public static final String INDEXED_PROP_FORMAT = "prop%d";
//
//      public InnerBuilder indexed(String value) {
//        prop(INDEXED_PROP_FORMAT.formatted(minimalIndex(INDEXED_PROP_FORMAT)), value);
//        return this;
//      }
//
//      @SuppressWarnings({"OptionalGetWithoutIsPresent", "SameParameterValue"})
//      private int minimalIndex(String pattern) {
//        return props$key == null || props$key.isEmpty() ? 1 :
//            IntStream.iterate(1, i -> i + 1)
//                     .filter(i -> !props$key.contains(pattern.formatted(i)))
//                     .findFirst()
//                     .getAsInt();
//      }
//    }
  }

//  public static class LambdaDslDemoBuilder {
//
//    @Getter(lazy = true)
//    private final InnerBuilder innerBuilder = new InnerBuilder();
//
//    @SuppressWarnings("LombokBuilder")
//    public LambdaDslDemoBuilder inner(UnaryOperator<Inner.InnerBuilder> initer) {
//      inner = initer.apply(getInnerBuilder()).build();
//      return this;
//    }

//  }
}
