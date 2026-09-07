package ru.vlapin.demo.lombokdemo.jackson.example;

import static org.assertj.core.api.Assertions.*;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ValuesJsonTest {

  JacksonTester<Values> valuesTester;
  JacksonTester<CamelEnum> camelEnumTester;
  JacksonTester<SnakeEnum> snakeEnumTester;
  JacksonTester<Container> containerTester;

  @Test
  @SneakyThrows
  @DisplayName("Values enum serializes correctly with default and overridden casing")
  void serializationTest() {
    assertThat(valuesTester.write(Values.FIRST_VALUE)).isEqualToJson("\"FirstValue\"");
    assertThat(valuesTester.write(Values.SECOND_VALUE)).isEqualToJson("\"SecondValue\"");
    assertThat(valuesTester.write(Values.THIRD_VALUE)).isEqualToJson("\"ThirdValue\"");
    assertThat(valuesTester.write(Values.FOURTH_VALUE)).isEqualToJson("\"fourthValue\"");
    assertThat(valuesTester.write(Values.FIFTH_VALUE)).isEqualToJson("\"fifth_value\"");
    assertThat(valuesTester.write(Values.SIXTH_VALUE)).isEqualToJson("\"6_value\"");
  }

  @Test
  @SneakyThrows
  @DisplayName("Values enum deserializes correctly with default and overridden casing")
  void deserializationTest() {
    assertThat(valuesTester.parse("\"FirstValue\"")).isEqualTo(Values.FIRST_VALUE);
    assertThat(valuesTester.parse("\"SecondValue\"")).isEqualTo(Values.SECOND_VALUE);
    assertThat(valuesTester.parse("\"ThirdValue\"")).isEqualTo(Values.THIRD_VALUE);
    assertThat(valuesTester.parse("\"fourthValue\"")).isEqualTo(Values.FOURTH_VALUE);
    assertThat(valuesTester.parse("\"fifth_value\"")).isEqualTo(Values.FIFTH_VALUE);
    assertThat(valuesTester.parse("\"6_value\"")).isEqualTo(Values.SIXTH_VALUE);
  }

  @EnumCamelCaseJson
  enum CamelEnum {
    FIRST_VALUE,
    @EnumSnakeCaseJson SECOND_VALUE,
    THIRD_VALUE
  }

  @Test
  @SneakyThrows
  @DisplayName("EnumCamelCaseJson works for enum and field overrides")
  void camelCaseEnumTest() {
    assertThat(camelEnumTester.write(CamelEnum.FIRST_VALUE)).isEqualToJson("\"firstValue\"");
    assertThat(camelEnumTester.write(CamelEnum.SECOND_VALUE)).isEqualToJson("\"second_value\"");

    assertThat(camelEnumTester.parse("\"firstValue\"")).isEqualTo(CamelEnum.FIRST_VALUE);
    assertThat(camelEnumTester.parse("\"second_value\"")).isEqualTo(CamelEnum.SECOND_VALUE);
  }

  @EnumSnakeCaseJson
  enum SnakeEnum {
    FIRST_VALUE,
    @EnumPascalCaseJson SECOND_VALUE
  }

  @Test
  @SneakyThrows
  @DisplayName("EnumSnakeCaseJson works for enum and field overrides")
  void snakeCaseEnumTest() {
    assertThat(snakeEnumTester.write(SnakeEnum.FIRST_VALUE)).isEqualToJson("\"first_value\"");
    assertThat(snakeEnumTester.write(SnakeEnum.SECOND_VALUE)).isEqualToJson("\"SecondValue\"");

    assertThat(snakeEnumTester.parse("\"first_value\"")).isEqualTo(SnakeEnum.FIRST_VALUE);
    assertThat(snakeEnumTester.parse("\"SecondValue\"")).isEqualTo(SnakeEnum.SECOND_VALUE);
  }

  enum DefaultEnum {
    FIRST_VALUE,
    @EnumCamelCaseJson SECOND_VALUE
  }

  @Test
  @SneakyThrows
  @DisplayName("Enum without annotation defaults to SCREAMING_SNAKE_CASE")
  void defaultEnumTest() {
    // When using EnumJsonSerializer or if registered
    String result = EnumJsonHelper.resolveValue(DefaultEnum.FIRST_VALUE, null);
    assertThat(result).isEqualTo("FIRST_VALUE");

    String overridden = EnumJsonHelper.resolveValue(DefaultEnum.SECOND_VALUE, null);
    assertThat(overridden).isEqualTo("secondValue");
  }

  record Container(Values value) {}

  @Test
  @SneakyThrows
  @DisplayName("Enum in object property serializes and deserializes correctly")
  void containerTest() {
    Container container = new Container(Values.FOURTH_VALUE);
    assertThat(containerTester.write(container)).isEqualToJson("{\"value\":\"fourthValue\"}");

    assertThat(containerTester.parse("{\"value\":\"fourthValue\"}")).isEqualTo(container);
  }
}
