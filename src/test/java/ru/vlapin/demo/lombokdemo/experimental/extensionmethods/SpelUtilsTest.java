package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import static java.util.Calendar.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.experimental.ExtensionMethod;
import lombok.val;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;

@ExtensionMethod(value = {
    SpelUtils.class,
    Objects.class,
}, suppressBaseMethods = false)
class SpelUtilsTest {

  public static final Inventor TESLA = new Inventor(
      "Nikola Tesla",
      new GregorianCalendar(1856, AUGUST, 9).getTime(),
      "Serbian",
      "induction motor", "commutator for dynamo");

  @Test
  @DisplayName("literal expression works correctly")
  void literalExpressionWorksCorrectlyTest() {
    // given
    @Language("SpEL") val byteArrayExpression = "'hello world'.concat('!').bytes";
    byte[] executed = byteArrayExpression.execute(byte[].class)
                                         .requireNonNull();

    // when
    assertThat(new String(executed))
        // then
        .isNotNull()
        .isEqualTo("hello world!");
  }

  @Test
  @DisplayName("array element access works correctly")
  void arrayElementAccessWorksCorrectlyTest() {
    // given
    @Language("SpEL") val expression = "inventions[0].toUpperCase";

    // when
    assertThat(TESLA.executeWith(expression, String.class))
        // then
        .isNotNull()
        .isEqualTo("induction motor".toUpperCase());
  }

  @Test
  @DisplayName("list literals works correctly")
  void listLiteralsWorksCorrectlyTest() {
    // given
    @Language("SpEL") val intListExpression = "{ 0, 1, 2, 3 }";

    // when
    assertThat(intListExpression.execute(new ParameterizedTypeReference<List<Integer>>() { }))
        // then
        .isNotNull().isNotEmpty().containsExactly(0, 1, 2, 3);
  }

  @Test
  @DisplayName("array literals works correctly")
  void arrayLiteralsWorksCorrectlyTest() {
    // given
    @Language("SpEL") val intArrayExpression = "new int[]{ 1, 2, 3, 4}";

    // when
    assertThat(intArrayExpression.execute(int[].class))
        // then
        .isNotNull()
        .hasSize(4);
  }

  @Test
  @DisplayName("map literals works correctly")
  void mapLiteralsWorksCorrectlyTest() {
    // given
    @Language("SpEL") val mapExpression = "{ name: 'Bob' }";

    // when
    assertThat(mapExpression.execute(new ParameterizedTypeReference<Map<String, String>>() { }))
        // then
        .isNotNull()
        .isNotEmpty()
        .containsOnlyKeys("name")
        .containsValue("Bob");
  }

  public record Inventor(
      String name,
      Date birthday,
      String nationality,
      String... inventions
  ) {
  }
}
