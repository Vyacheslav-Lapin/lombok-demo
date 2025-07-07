package ru.vlapin.demo.lombokdemo.stable.value;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.common.ReflectionUtils.*;

import java.lang.reflect.Constructor;
import lombok.experimental.ExtensionMethod;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.RecordUtils;

record PackagePrivateConstructorDemo(String message) {

  @SuppressWarnings("RedundantRecordConstructor")
  //private
  PackagePrivateConstructorDemo(String message) {
    this.message = message;
  }
}

@ExtensionMethod(suppressBaseMethods = false,
                 value = RecordUtils.class)
class PackagePrivateConstructorDemoTest {

  @Test
  @DisplayName(" works correctly")
  void worksCorrectlyTest() {
    // given
    val constructor = PackagePrivateConstructorDemo.class.canonicalConstructor();

    // when
    assertThat(constructor)
        // then
        .extracting(Constructor::getModifiers)
        .matches(isPackagePrivate::test);
  }
}
