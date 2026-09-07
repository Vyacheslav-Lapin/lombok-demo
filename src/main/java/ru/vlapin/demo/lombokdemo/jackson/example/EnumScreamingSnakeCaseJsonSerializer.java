package ru.vlapin.demo.lombokdemo.jackson.example;

public class EnumScreamingSnakeCaseJsonSerializer extends EnumJsonSerializer {
  public EnumScreamingSnakeCaseJsonSerializer() {
    super(CaseType.SCREAMING_SNAKE_CASE);
  }
}
