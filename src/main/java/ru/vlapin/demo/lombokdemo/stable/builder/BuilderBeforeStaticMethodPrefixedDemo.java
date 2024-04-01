package ru.vlapin.demo.lombokdemo.stable.builder;

import java.time.LocalDate;
import lombok.Builder;
import lombok.experimental.UtilityClass;

@SuppressWarnings({"java:S125", "CommentedOutCode", "unused"})

@UtilityClass
public class BuilderBeforeStaticMethodPrefixedDemo {

  @Builder(builderMethodName = "beforeNowCaller",
           buildMethodName = "call",
           builderClassName = "BeforeNowCaller",
           setterPrefix = "with")
  public LocalDate beforeNow(int days, int months) {
    return LocalDate.now().minusDays(days).minusMonths(months);
  }

//public static LocalDateBuilder beforeNowCaller() {
//  return new LocalDateBuilder();
//}

//@ToString
//@FieldDefaults(level = PRIVATE)
//@NoArgsConstructor(access = PACKAGE)
//public static class BeforeNowCaller {
//  int days;
//  int months;

//  public LocalDate call() {
//    return beforeNow(this.days, this.months);
//  }
//  public BeforeNowCaller withDays(int days) { this.days = days; return this; }
//  public BeforeNowCaller withMonths(int months) { this.months = months; return this; }
//}
}
