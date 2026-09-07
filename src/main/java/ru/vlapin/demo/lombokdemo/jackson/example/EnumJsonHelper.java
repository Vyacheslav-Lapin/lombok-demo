package ru.vlapin.demo.lombokdemo.jackson.example;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.Objects;

final class EnumJsonHelper {

  private EnumJsonHelper() {
  }

  public static String resolveValue(Enum<?> value, CaseType defaultCaseType) {
    if (value == null) {
      return null;
    }
    if (!Objects.equals(value.toString(), value.name())) {
      return value.toString();
    }

    Class<?> enumClass = value.getDeclaringClass();
    try {
      Field field = enumClass.getField(value.name());
      CaseType fieldCaseType = getCaseType(field);
      if (fieldCaseType != null) {
        return fieldCaseType.format(value.name());
      }
    } catch (NoSuchFieldException ignored) {
    }

    CaseType classCaseType = getCaseType(enumClass);
    if (classCaseType != null) {
      return classCaseType.format(value.name());
    }

    if (defaultCaseType != null) {
      return defaultCaseType.format(value.name());
    }

    return CaseType.SCREAMING_SNAKE_CASE.format(value.name());
  }

  public static CaseType getCaseType(AnnotatedElement element) {
    if (element.isAnnotationPresent(EnumCamelCaseJson.class)) {
      return CaseType.CAMEL_CASE;
    }
    if (element.isAnnotationPresent(EnumPascalCaseJson.class)) {
      return CaseType.PASCAL_CASE;
    }
    if (element.isAnnotationPresent(EnumSnakeCaseJson.class)) {
      return CaseType.SNAKE_CASE;
    }
    if (element.isAnnotationPresent(EnumScreamingSnakeCaseJson.class)) {
      return CaseType.SCREAMING_SNAKE_CASE;
    }
    return null;
  }
}
