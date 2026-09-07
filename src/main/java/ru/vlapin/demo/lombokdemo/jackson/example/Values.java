package ru.vlapin.demo.lombokdemo.jackson.example;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Tolerate;

@EnumPascalCaseJson
@RequiredArgsConstructor
@Getter(onMethod_ = @Override)
public enum Values {
  FIRST_VALUE,
  SECOND_VALUE,
  THIRD_VALUE,
  @EnumCamelCaseJson FOURTH_VALUE,
  @EnumSnakeCaseJson FIFTH_VALUE,
  SIXTH_VALUE("6_value"),
  ;

  String toString;

  @Tolerate
  Values() {
    this.toString = name();
  }
}
