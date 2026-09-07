package ru.vlapin.demo.lombokdemo.jackson.example;

public class EnumPascalCaseJsonDeserializer extends EnumJsonDeserializer {

  public EnumPascalCaseJsonDeserializer() {
    super(CaseType.PASCAL_CASE);
  }

  @SuppressWarnings("rawtypes")
  public EnumPascalCaseJsonDeserializer(Class<? extends Enum> enumClass) {
    super(CaseType.PASCAL_CASE, enumClass);
  }

  @Override
  @SuppressWarnings("rawtypes")
  protected EnumJsonDeserializer newInstance(CaseType defaultCaseType, Class<? extends Enum> enumClass) {
    return new EnumPascalCaseJsonDeserializer(enumClass);
  }
}
