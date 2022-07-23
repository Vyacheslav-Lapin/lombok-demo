package ru.vlapin.demo.lombokdemo.stable.getter;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BeanspecExampleTests {

  @Test
  @SneakyThrows
  @DisplayName("Beanspec config property works correctly")
  void beanspecConfigPropertyWorksCorrectlyTest() {
    assertThat(new BeanspecExample()
                   .setuShape(5)
                   .getuShape())
        .isEqualTo(5);
  }
}
