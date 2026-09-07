package ru.vlapin.demo.lombokdemo.jackson.example;

import com.google.common.base.CaseFormat;

public enum CaseType {
  CAMEL_CASE {
    @Override
    public String format(String name) {
      CaseFormat source = detectSourceFormat(name);
      return source.to(CaseFormat.LOWER_CAMEL, name);
    }
  },
  PASCAL_CASE {
    @Override
    public String format(String name) {
      CaseFormat source = detectSourceFormat(name);
      return source.to(CaseFormat.UPPER_CAMEL, name);
    }
  },
  SNAKE_CASE {
    @Override
    public String format(String name) {
      CaseFormat source = detectSourceFormat(name);
      return source.to(CaseFormat.LOWER_UNDERSCORE, name);
    }
  },
  SCREAMING_SNAKE_CASE {
    @Override
    public String format(String name) {
      CaseFormat source = detectSourceFormat(name);
      return source.to(CaseFormat.UPPER_UNDERSCORE, name);
    }
  };

  public abstract String format(String name);

  private static CaseFormat detectSourceFormat(String name) {
    if (name.contains("_")) {
      return name.equals(name.toLowerCase()) ? CaseFormat.LOWER_UNDERSCORE : CaseFormat.UPPER_UNDERSCORE;
    }
    if (name.contains("-")) {
      return CaseFormat.LOWER_HYPHEN;
    }
    if (name.equals(name.toUpperCase())) {
      return CaseFormat.UPPER_UNDERSCORE;
    }
    if (name.equals(name.toLowerCase())) {
      return CaseFormat.LOWER_CAMEL;
    }
    if (Character.isUpperCase(name.charAt(0))) {
      return CaseFormat.UPPER_CAMEL;
    }
    return CaseFormat.LOWER_CAMEL;
  }
}
