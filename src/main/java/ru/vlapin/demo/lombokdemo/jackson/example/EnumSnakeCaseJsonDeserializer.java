package ru.vlapin.demo.lombokdemo.jackson.example;

public class EnumSnakeCaseJsonDeserializer extends EnumJsonDeserializer {

  public EnumSnakeCaseJsonDeserializer() {
    super(CaseType.SNAKE_CASE);
  }

  @SuppressWarnings("rawtypes")
  public EnumSnakeCaseJsonDeserializer(Class<? extends Enum> enumClass) {
    super(CaseType.SNAKE_CASE, enumClass);
  }

  @Override
  @SuppressWarnings("rawtypes")
  protected EnumJsonDeserializer newInstance(CaseType defaultCaseType, Class<? extends Enum> enumClass) {
    return new EnumSnakeCaseJsonDeserializer(enumClass);
  }
}
