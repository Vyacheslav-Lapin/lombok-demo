package ru.vlapin.demo.lombokdemo.stable.data.extraprivate;

import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.ReflectionUtils;

import java.lang.reflect.Constructor;

import static org.assertj.core.api.Assertions.*;

/**
 * ExtraPrivateDemoTest.
 */
@ExtensionMethod({
    ReflectionUtils.class,
})
class ExtraPrivateDemoTest {

  @SneakyThrows
  private static <T> T noArgsConstructor(Constructor<T> constructor) {
    return constructor.newInstance();
  }

  @Test
  @SneakyThrows
  @SuppressWarnings("DataFlowIssue")
  @DisplayName("ExtraPrivate settings param works correctly")
  void extraPrivateSettingsParamWorksCorrectlyTest() {
    // given
    val constructor = ExtraPrivateDemo.class
        .getDeclaredConstructor();

    constructor.setAccessible(true);

    // when
    assertThat(constructor)
        // then
        .isNotNull()
        .extracting(ExtraPrivateDemoTest::noArgsConstructor)
        .isEqualTo(new ExtraPrivateDemo(null));
  }
}
