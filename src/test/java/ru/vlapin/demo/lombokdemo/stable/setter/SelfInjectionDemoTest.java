package ru.vlapin.demo.lombokdemo.stable.setter;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
    "springdoc.api-docs.enabled=false",
    "springdoc.swagger-ui.enabled=false"
})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class SelfInjectionDemoTest {

  SelfInjectionDemo selfInjectionDemo;

  @Test
  @DisplayName("Self-injection works correctly")
  void selfInjectionWorksCorrectlyTest() {
    // given
    selfInjectionDemo.method();
  }
}
