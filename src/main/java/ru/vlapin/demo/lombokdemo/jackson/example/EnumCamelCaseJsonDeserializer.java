package ru.vlapin.demo.lombokdemo.jackson.example;

public class EnumCamelCaseJsonDeserializer extends EnumJsonDeserializer {

  public EnumCamelCaseJsonDeserializer() {
    super(CaseType.CAMEL_CASE);
  }

  @SuppressWarnings("rawtypes")
  public EnumCamelCaseJsonDeserializer(Class<? extends Enum> enumClass) {
    super(CaseType.CAMEL_CASE, enumClass);
  }

  @Override
  @SuppressWarnings("rawtypes")
  protected EnumJsonDeserializer newInstance(CaseType defaultCaseType, Class<? extends Enum> enumClass) {
    return new EnumCamelCaseJsonDeserializer(enumClass);
  }
}
