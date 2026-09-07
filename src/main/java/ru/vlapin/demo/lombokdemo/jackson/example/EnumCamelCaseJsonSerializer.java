package ru.vlapin.demo.lombokdemo.jackson.example;

public class EnumCamelCaseJsonSerializer extends EnumJsonSerializer {
  public EnumCamelCaseJsonSerializer() {
    super(CaseType.CAMEL_CASE);
  }
}
