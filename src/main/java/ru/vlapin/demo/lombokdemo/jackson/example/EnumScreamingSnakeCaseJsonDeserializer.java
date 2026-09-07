package ru.vlapin.demo.lombokdemo.jackson.example;

public class EnumScreamingSnakeCaseJsonDeserializer extends EnumJsonDeserializer {

  public EnumScreamingSnakeCaseJsonDeserializer() {
    super(CaseType.SCREAMING_SNAKE_CASE);
  }

  @SuppressWarnings("rawtypes")
  public EnumScreamingSnakeCaseJsonDeserializer(Class<? extends Enum> enumClass) {
    super(CaseType.SCREAMING_SNAKE_CASE, enumClass);
  }

  @Override
  @SuppressWarnings("rawtypes")
  protected EnumJsonDeserializer newInstance(CaseType defaultCaseType, Class<? extends Enum> enumClass) {
    return new EnumScreamingSnakeCaseJsonDeserializer(enumClass);
  }
}
