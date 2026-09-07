package ru.vlapin.demo.lombokdemo.jackson.example;

public class EnumPascalCaseJsonSerializer extends EnumJsonSerializer {
  public EnumPascalCaseJsonSerializer() {
    super(CaseType.PASCAL_CASE);
  }
}
