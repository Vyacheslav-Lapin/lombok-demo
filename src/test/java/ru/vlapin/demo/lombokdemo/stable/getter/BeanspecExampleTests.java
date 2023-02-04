package ru.vlapin.demo.lombokdemo.stable.getter;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BeanspecExampleTests {

  @Test
  @SneakyThrows
  @DisplayName("Beanspec config property works correctly")
  void beanSpecConfigPropertyWorksCorrectlyTest() {
    Assertions.assertThat(new BeanspecExample()
                              .setuShape(5)
                              .getuShape())
        .isEqualTo(5);
  }
}
