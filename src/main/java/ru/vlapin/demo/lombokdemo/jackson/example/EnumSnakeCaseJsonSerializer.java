package ru.vlapin.demo.lombokdemo.jackson.example;

public class EnumSnakeCaseJsonSerializer extends EnumJsonSerializer {
  public EnumSnakeCaseJsonSerializer() {
    super(CaseType.SNAKE_CASE);
  }
}
