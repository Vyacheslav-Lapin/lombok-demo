package ru.vlapin.demo.lombokdemo.jackson.example;

import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.BeanProperty;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.deser.std.StdDeserializer;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EnumJsonDeserializer extends StdDeserializer<Enum<?>> {

  CaseType defaultCaseType;

  @SuppressWarnings("rawtypes")
  Class<? extends Enum> enumClass;

  public EnumJsonDeserializer() {
    this(CaseType.SNAKE_CASE);
  }

  public EnumJsonDeserializer(CaseType defaultCaseType) {
    this(defaultCaseType, null);
  }

  @SuppressWarnings("rawtypes")
  public EnumJsonDeserializer(CaseType defaultCaseType, Class<? extends Enum> enumClass) {
    super(enumClass != null ? enumClass : Enum.class);
    this.defaultCaseType = defaultCaseType;
    this.enumClass = enumClass;
  }

  @Override
  @SuppressWarnings({"unchecked", "rawtypes"})
  public ValueDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JacksonException {
    JavaType targetType = ctxt.getContextualType() != null
        ? ctxt.getContextualType()
        : (property != null ? property.getType() : null);

    Class<?> rawClass = targetType != null ? targetType.getRawClass() : null;
    if (rawClass != null && Enum.class.isAssignableFrom(rawClass)) {
      return newInstance(defaultCaseType, (Class<? extends Enum>) rawClass);
    }
    return this;
  }

  @SuppressWarnings("rawtypes")
  protected EnumJsonDeserializer newInstance(CaseType defaultCaseType, Class<? extends Enum> enumClass) {
    return new EnumJsonDeserializer(defaultCaseType, enumClass);
  }

  @Override
  @SuppressWarnings({"unchecked", "rawtypes"})
  public Enum<?> deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
    String text = p.getValueAsString();
    if (text == null) {
      return null;
    }

    Class<? extends Enum> targetClass = this.enumClass;
    if (targetClass == null) {
      JavaType contextualType = ctxt.getContextualType();
      if (contextualType != null && Enum.class.isAssignableFrom(contextualType.getRawClass())) {
        targetClass = (Class<? extends Enum>) contextualType.getRawClass();
      }
    }

    if (targetClass == null) {
      return null;
    }

    for (Enum<?> constant : targetClass.getEnumConstants()) {
      String expected = EnumJsonHelper.resolveValue(constant, defaultCaseType);
      if (Objects.equals(text, expected)) {
        return constant;
      }
    }

    return (Enum<?>) ctxt.handleWeirdStringValue(
        targetClass,
        text,
        "Cannot deserialize value '%s' to enum %s",
        text,
        targetClass.getName()
    );
  }
}
