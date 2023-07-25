package ru.vlapin.demo.lombokdemo.stable.equalshashcode;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * CacheDemoTest.
 */
class CacheDemoTest {

  @Test
  @DisplayName("hashCode cache works correctly")
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
