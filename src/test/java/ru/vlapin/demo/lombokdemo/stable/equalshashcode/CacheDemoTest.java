package ru.vlapin.demo.lombokdemo.stable.equalshashcode;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * CacheDemoTest.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class CacheDemoTest {

  @Test
  //@DisplayName("hashCode cache works correctly")
  void hashCodeCacheWorksCorrectlyTest() {
    // given
    val obj = new CacheDemo(1, "Lor");
    val hashCode = obj.hashCode();

    // when
    assertThat(obj)
        .extracting(cacheDemo ->
            cacheDemo.setX(2))
        .extracting(Object::hashCode)
        .isEqualTo(hashCode);
  }
}
