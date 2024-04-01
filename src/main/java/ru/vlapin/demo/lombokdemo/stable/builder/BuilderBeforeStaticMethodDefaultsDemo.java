package ru.vlapin.demo.lombokdemo.stable.builder;

import java.time.LocalDate;
import lombok.Builder;
import lombok.experimental.UtilityClass;

@SuppressWarnings({"java:S125", "CommentedOutCode", "unused"})

@UtilityClass
public class BuilderBeforeStaticMethodDefaultsDemo {

  @Builder(builderMethodName = "beforeNowCaller",
           buildMethodName = "call",
           builderClassName = "BeforeNowCaller",
           setterPrefix = "with")
  public LocalDate beforeNow(int days, int months) {
    if (days == 0) days = 1; // default
    if (months == 0) months = 1; // default
    return LocalDate.now().minusDays(days).minusMonths(months);
  }

//public static BeforeNowCaller beforeNowCaller() {
//  return new BeforeNowCaller();
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
