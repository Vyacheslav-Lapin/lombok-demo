package ru.vlapin.demo.lombokdemo.stable.data.extraprivate;

import static org.assertj.core.api.Assertions.*;

import java.lang.reflect.Constructor;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * ExtraPrivateDemoTest.
 */
//@ExtensionMethod({
//    ReflectionUtils.class,
//})
@DisplayNameGeneration(ReplaceCamelCase.class)
class ExtraPrivateDemoTest {

  @SneakyThrows
  private static <T> T noArgsConstructor(Constructor<T> constructor) {
    return constructor.newInstance();
  }

  @Test
  @SneakyThrows
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
