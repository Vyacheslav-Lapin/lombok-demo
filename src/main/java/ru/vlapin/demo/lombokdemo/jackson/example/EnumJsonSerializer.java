package ru.vlapin.demo.lombokdemo.jackson.example;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EnumJsonSerializer extends StdSerializer<Enum<?>> {

  CaseType defaultCaseType;

  public EnumJsonSerializer() {
    this(CaseType.SNAKE_CASE);
  }

  public EnumJsonSerializer(CaseType defaultCaseType) {
    super(Enum.class);
    this.defaultCaseType = defaultCaseType;
  }

  @Override
  public void serialize(Enum<?> value, JsonGenerator gen, SerializationContext serializers) throws JacksonException {
    if (value == null) {
      gen.writeNull();
      return;
    }
    String result = EnumJsonHelper.resolveValue(value, defaultCaseType);
    gen.writeString(result);
  }
}
