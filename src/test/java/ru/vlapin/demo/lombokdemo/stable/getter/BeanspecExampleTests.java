package ru.vlapin.demo.lombokdemo.stable.getter;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.stable.getter.beanspec.BeanspecExample;

import static org.assertj.core.api.Assertions.assertThat;

class BeanspecExampleTests {

  @Test
  @DisplayName("Beanspec config property works correctly")
  void beanSpecConfigPropertyWorksCorrectlyTest() {
    val bse = new BeanspecExample();

    //bse.setUShape(5);
    bse.setuShape(5);

    assertThat(bse)
      //.extracting(BeanspecExample::getUShape)
        .extracting(BeanspecExample::getuShape)
        .isEqualTo(5);
  }
}
