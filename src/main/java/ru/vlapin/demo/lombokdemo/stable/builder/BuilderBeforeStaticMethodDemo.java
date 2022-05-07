package ru.vlapin.demo.lombokdemo.stable.builder;

import java.time.LocalDate;

import lombok.Builder;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BuilderBeforeStaticMethodDemo {

  @Builder
  public LocalDate beforeNow(int days,
                                    int months//,
                                    //@Singular List<String> children
                                    ) {
    return LocalDate.now()
               .minusDays(days)
               .minusMonths(months);
  }

//  @Setter
//  @ToString
//  @Accessors(fluent = true)
//  @FieldDefaults(level = PRIVATE)
//  @NoArgsConstructor(access = PACKAGE)
//  public static class LocalDateBuilder {
//    int days;
//    int months;
//
//    public LocalDate build() {
//      return BuilderBeforeStaticMethodDemo.beforeNow(days, months);
//    }
//  }
//
//  public static BuilderBeforeStaticMethodDemo.LocalDateBuilder builder() {
//    return new BuilderBeforeStaticMethodDemo.LocalDateBuilder();
//  }
}
