package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import java.util.Arrays;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    String iAmNull = null;

    assertThat(iAmNull.getIfNull("hELlO, WORlD!".toTitleCase())).isNotNull()
        .isEqualTo("Hello, world!");
  }

  @Test
  @DisplayName(" works correctly")
  void worksCorrectlyTest() {
    int[] x = {5, 3, 8, 2};
    //noinspection ImplicitArrayToString
    log.info(x.toString()); // [5, 3, 8, 2]
    //    log.info(Arrays.toString(x)); // [5, 3, 8, 2]
  }
}
