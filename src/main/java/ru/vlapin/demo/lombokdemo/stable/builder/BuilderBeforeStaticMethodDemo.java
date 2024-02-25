package ru.vlapin.demo.lombokdemo.stable.builder;

import java.time.LocalDate;
import lombok.Builder;
import lombok.experimental.UtilityClass;

@SuppressWarnings({"java:S125", "CommentedOutCode"})
@UtilityClass
public class BuilderBeforeStaticMethodDemo {

  @Builder(builderMethodName = "beforeNowBuilder",
           buildMethodName = "call")
  private LocalDate beforeNow(int days,
                              int months) {
    return LocalDate.now()
               .minusDays(days)
               .minusMonths(months);
  }

//public static LocalDateBuilder beforeNowBuilder() {
//  return new LocalDateBuilder();
//}

//@Setter
//@ToString
//@Accessors(fluent = true)
//@FieldDefaults(level = PRIVATE)
//@NoArgsConstructor(access = PACKAGE)
//public static class LocalDateBuilder {
//  int days;
//  int months;

//  public LocalDate call() {
//    return beforeNow(this.days, this.months);
//  }
//}
}
