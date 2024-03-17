package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils;

@SuppressWarnings("RedundantSuppression")

@Slf4j
@ExtensionMethod({
    StringExtensions.class,
    ObjectExtensions.class,
    Arrays.class,
})
@DisplayNameGeneration(TestUtils.ReplaceCamelCase.class)
class StringStringExtensionsInterfaceTest {

  @Test
  @DisplayName("ExtensionMethod from interface works correctly")
  void extensionMethodFromInterfaceWorksCorrectlyTest() {
    //noinspection DataFlowIssue
    String iAmNull = null;

    //noinspection DataFlowIssue
    assertThat(iAmNull.orIfNull(() -> "hELlO, WORlD!".toTitleCase()))
        .isNotNull()
        .isEqualTo("Hello, world!");
  }

  @Test
  @SneakyThrows
  @DisplayName("ExtensionMethod exchange present method correctly")
  void extensionMethodExchangePresentMethodCorrectlyTest() {
    // given
    //noinspection MismatchedReadAndWriteOfArray
    int[] x = {5, 3, 8, 2};

    // when
    //noinspection ImplicitArrayToString
    log.info(x.toString()); // [5, 3, 8, 2]
    //log.info(Arrays.toString(x)); // [5, 3, 8, 2]

    // when
    //noinspection ImplicitArrayToString
    assertThat(x.toString()).isNotNull()
        // then
        .isEqualTo("[5, 3, 8, 2]");
  }
}
