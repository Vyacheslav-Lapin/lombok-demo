package ru.vlapin.demo.lombokdemo.common;

import static org.assertj.core.api.Assertions.*;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedFunction1;
import io.vavr.CheckedFunction2;
import io.vavr.CheckedPredicate;
import lombok.experimental.ExtensionMethod;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
@ExtensionMethod(value = ScopeFunctions.class, suppressBaseMethods = false)
class ScopeFunctionsTest {

  @Test
  @DisplayName("peakWith method applies CheckedConsumer and does not alter the original object reference")
  void peakWithAppliesConsumerAndReturnsOriginalObjectTest() {
    // given
    val stringBuilder = new StringBuilder("Hello");
    final CheckedConsumer<StringBuilder> appendWorldCheckedConsumer = sb -> sb.append(" World!");

    // when
    val result = stringBuilder.peakWith(appendWorldCheckedConsumer);

    // then
    assertThat(result)
        .isSameAs(stringBuilder)
        .asString()
        .isEqualTo("Hello World!");
  }

  @Test
  @DisplayName("peakWith method returns the same object when CheckedConsumer does nothing")
  void peakWithReturnsSameObjectWhenConsumerDoesNothingTest() {
    // given
    val stringBuilder = new StringBuilder("Hello");
    final CheckedConsumer<StringBuilder> noOpCheckedConsumer = _ -> {
    };

    // when
    val result = stringBuilder.peakWith(noOpCheckedConsumer);

    // then
    assertThat(result)
        .isSameAs(stringBuilder)
        .asString()
        .isEqualTo("Hello");
  }

  @Test
  @SuppressWarnings("MismatchedQueryAndUpdateOfStringBuilder")
  @DisplayName("peakWith method throws RuntimeException when CheckedConsumer throws an exception")
  void peakWithThrowsRuntimeExceptionOnCheckedConsumerExceptionTest() {
    // given
    val stringBuilder = new StringBuilder("Hello");
    final CheckedConsumer<StringBuilder> exceptionThrowingCheckedConsumer = _ -> {
      throw new Exception("Simulated exception");
    };

    // when / then
    assertThatThrownBy(() -> stringBuilder.peakWith(exceptionThrowingCheckedConsumer))
        .isInstanceOf(Exception.class)
        .hasMessageContaining("Simulated exception");
  }

  /**
   * Test class for {@link ScopeFunctions#with(Object, io.vavr.CheckedConsumer)}. The {@code with} method facilitates applying a given
   * operation (represented by {@link io.vavr.CheckedConsumer}) on an object, without returning any result.
   */
  @Test
  @DisplayName("with method executes CheckedConsumer successfully on a non-null object")
  void withExecutesConsumerSuccessfullyOnNonNullObjectTest() {
    // given
    val stringBuilder = new StringBuilder("Hello");

    // when
    stringBuilder.with(sb -> sb.append(" World!"));

    // then
    assertThat(stringBuilder.toString())
        .isEqualTo("Hello World!");
  }

  @Test
  @SuppressWarnings("MismatchedQueryAndUpdateOfStringBuilder")
  @DisplayName("with method throws RuntimeException on CheckedConsumer exception")
  void withThrowsRuntimeExceptionOnCheckedConsumerExceptionTest() {
    // given
    val stringBuilder = new StringBuilder("Hello");
    final CheckedConsumer<StringBuilder> exceptionThrowingCheckedConsumer = _ -> {
      throw new Exception("Simulated exception");
    };

    // when / then
    assertThatThrownBy(() ->
        stringBuilder.with(exceptionThrowingCheckedConsumer))
        .isInstanceOf(Exception.class)
        .hasMessageContaining("Simulated exception");
  }

  @Test
  @SuppressWarnings("MismatchedQueryAndUpdateOfStringBuilder")
  @DisplayName("mapWith method transforms object and returns result")
  void mapWithTransformsObjectAndReturnsResultTest() {
    // given
    val stringBuilder = new StringBuilder("Hello");

    // when
    val result = stringBuilder.mapWith(StringBuilder::toString);

    // then
    assertThat(result).isEqualTo("Hello");
  }

  @Test
  @SuppressWarnings("MismatchedQueryAndUpdateOfStringBuilder")
  @DisplayName("mapWith method throws RuntimeException on transformation exception")
  void mapWithThrowsRuntimeExceptionOnTransformationExceptionTest() {
    // given
    val stringBuilder = new StringBuilder("Hello");
    final CheckedFunction1<StringBuilder, Integer> exceptionThrowingMapper = _ -> {
      throw new Exception("Simulated exception");
    };

    // when / then
    assertThatThrownBy(() -> stringBuilder.mapWith(exceptionThrowingMapper))
        .isInstanceOf(Exception.class)
        .hasMessageContaining("Simulated exception");
  }

  @Test
  @DisplayName("doWithIfNotNull applies mapper function when operand is not null")
  void doWithIfNotNullAppliesMapperFunctionWhenOperandIsNotNullTest() {
    // given
    val stringBuilder = new StringBuilder("Hello");

    // when
    val result = stringBuilder.doWithIfNotNull(" World!", StringBuilder::append);

    // then
    assertThat(result)
        .isSameAs(stringBuilder)
        .asString()
        .isEqualTo("Hello World!");
  }

  @Test
  @SuppressWarnings("ConstantValue")
  @DisplayName("doWithIfNotNull returns original object when operand is null")
  void doWithIfNotNullReturnsOriginalObjectWhenOperandIsNullTest() {
    // given
    val stringBuilder = new StringBuilder("Hello");
    String operand = null;

    // when
    val result = stringBuilder.doWithIfNotNull(operand, StringBuilder::append);

    // then
    assertThat(result).isSameAs(stringBuilder);
    assertThat(result.toString()).isEqualTo("Hello");
  }

  @Test
  @SuppressWarnings("MismatchedQueryAndUpdateOfStringBuilder")
  @DisplayName("doWithIfNotNull throws RuntimeException when mapper function throws exception")
  void doWithIfNotNullThrowsRuntimeExceptionWhenMapperFunctionThrowsExceptionTest() {
    // given
    val stringBuilder = new StringBuilder("Hello");
    final CheckedFunction2<StringBuilder, String, StringBuilder> exceptionThrowingMapper =
        (_, _) -> {
          throw new Exception("Simulated exception");
        };

    // when / then
    assertThatThrownBy(() ->
        stringBuilder.doWithIfNotNull(" World!", exceptionThrowingMapper))
        .isInstanceOf(Exception.class)
        .hasMessageContaining("Simulated exception");
  }

  @Test
  @SuppressWarnings("MismatchedQueryAndUpdateOfStringBuilder")
  @DisplayName("mapWithIfOrElse applies mapperIfTrue when condition is true")
  void mapWithIfOrElseAppliesMapperIfTrueWhenConditionIsTrueTest() {
    // given
    val stringBuilder = new StringBuilder("Hello");
    final CheckedPredicate<StringBuilder> condition = sb -> !sb.isEmpty();
    final CheckedFunction1<StringBuilder, String> mapperIfTrue = sb -> sb.toString() + " World!";
    final CheckedFunction1<StringBuilder, String> mapperIfFalse = sb -> sb.toString() + " Nobody!";

    // when
    val result = stringBuilder.mapWithIfOrElse(condition, mapperIfTrue, mapperIfFalse);

    // then
    assertThat(result).isEqualTo("Hello World!");
  }

  @Test
  @DisplayName("orIfNull returns alternative when input is null")
  void orIfNullReturnsAlternativeWhenInputIsNullTest() {
    // given
    String input = null;
    String alternative = "Alternative";

    // when
    val result = input.orIfNull(alternative);

    // then
    assertThat(result)
        .isNotNull()
        .isEqualTo(alternative);
  }

  @Test
  @DisplayName("orIfNull returns original when input is not null")
  void orIfNullReturnsOriginalWhenInputIsNotNullTest() {
    // given
    val input = "Original";

    // when
    val result = input.orIfNull("Alternative");

    // then
    assertThat(result).isEqualTo(input);
  }

  @Test
  @DisplayName("orIfNull throws exception when alternative source throws")
  void orIfNullThrowsExceptionWhenAlternativeSourceThrowsTest() {
    // given
    String input = null;
    CheckedFunction0<String> exceptionThrowingAlternative = () -> {
      throw new RuntimeException("Simulated exception");
    };

    // when / then
    assertThatThrownBy(() -> input.orIfNull(exceptionThrowingAlternative))
        .isInstanceOf(RuntimeException.class)
        .hasMessageContaining("Simulated exception");
  }

  @Test
  @DisplayName("orIfNull returns alternative from source when input is null")
  void orIfNullReturnsAlternativeFromSourceWhenInputIsNullTest() {
    // given
    String input = null;

    // when
    val result = input.orIfNull(() -> "Alternative From Source");

    // then
    assertThat(result).isEqualTo("Alternative From Source");
  }

  @Test
  @SuppressWarnings("MismatchedQueryAndUpdateOfStringBuilder")
  @DisplayName("mapWithIfOrElse applies mapperIfFalse when condition is false")
  void mapWithIfOrElseAppliesMapperIfFalseWhenConditionIsFalseTest() {
    // given
    val stringBuilder = new StringBuilder("Hello");
    final CheckedPredicate<StringBuilder> condition = CharSequence::isEmpty;
    final CheckedFunction1<StringBuilder, String> mapperIfTrue = sb -> sb.toString() + " World!";
    final CheckedFunction1<StringBuilder, String> mapperIfFalse = sb -> sb.toString() + " Nobody!";

    // when
    val result = stringBuilder.mapWithIfOrElse(condition, mapperIfTrue, mapperIfFalse);

    // then
    assertThat(result).isEqualTo("Hello Nobody!");
  }

  @Test
  @SuppressWarnings("MismatchedQueryAndUpdateOfStringBuilder")
  @DisplayName("mapWithIfOrElse throws RuntimeException when mapperIfTrue throws exception and condition is true")
  void mapWithIfOrElseThrowsExceptionForMapperIfTrueWithConditionTrueTest() {
    // given
    val stringBuilder = new StringBuilder("Hello");
    final CheckedPredicate<StringBuilder> condition = sb -> !sb.isEmpty();
    final CheckedFunction1<StringBuilder, String> mapperIfTrue = _ -> {
      throw new Exception("Simulated exception in mapperIfTrue");
    };
    final CheckedFunction1<StringBuilder, String> mapperIfFalse = sb -> sb.toString() + " Nobody!";

    // when / then
    assertThatThrownBy(() -> stringBuilder.mapWithIfOrElse(condition, mapperIfTrue, mapperIfFalse))
        .isInstanceOf(Exception.class)
        .hasMessageContaining("Simulated exception in mapperIfTrue");
  }

  @Test
  @SuppressWarnings("MismatchedQueryAndUpdateOfStringBuilder")
  @DisplayName("mapWithIfOrElse throws RuntimeException when mapperIfFalse throws exception and condition is false")
  void mapWithIfOrElseThrowsExceptionForMapperIfFalseWithConditionFalseTest() {
    // given
    val stringBuilder = new StringBuilder("Hello");
    final CheckedPredicate<StringBuilder> condition = CharSequence::isEmpty;
    final CheckedFunction1<StringBuilder, String> mapperIfTrue = sb -> sb.toString() + " World!";
    final CheckedFunction1<StringBuilder, String> mapperIfFalse = _ -> {
      throw new Exception("Simulated exception in mapperIfFalse");
    };

    // when / then
    assertThatThrownBy(() -> stringBuilder.mapWithIfOrElse(condition, mapperIfTrue, mapperIfFalse))
        .isInstanceOf(Exception.class)
        .hasMessageContaining("Simulated exception in mapperIfFalse");
  }
}
