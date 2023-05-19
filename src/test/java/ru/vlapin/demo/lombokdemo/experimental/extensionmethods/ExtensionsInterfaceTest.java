package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@ExtensionMethod({
    Extensions.class,
    // ExtensionsInterface.class,
    Arrays.class,
})
class ExtensionsInterfaceTest {

  @Test
  @DisplayName("ExtensionMethod from interface works correctly")
  void extensionMethodFromInterfaceWorksCorrectlyTest() {
    //noinspection DataFlowIssue
    String iAmNull = null;

    assertThat(iAmNull.getIfNull("hELlO, WORlD!".toTitleCase())).isNotNull()
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
    //    log.info(Arrays.toString(x)); // [5, 3, 8, 2]

    // when
    //noinspection ImplicitArrayToString
    assertThat(x.toString()).isNotNull()
        // then
        .isEqualTo("[5, 3, 8, 2]");
  }
}
