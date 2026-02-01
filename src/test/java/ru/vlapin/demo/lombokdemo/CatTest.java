package ru.vlapin.demo.lombokdemo;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@SpringBootTest(properties = {
    "management.metrics.export.defaults.enabled=false",
    // Отключаем проблемную часть springdoc, которая тянет Data REST/HATEOAS(HAL) и валит контекст на Spring Boot 4.x
    "spring.autoconfigure.exclude=" +
        "org.springdoc.core.configuration.SpringDocDataRestConfiguration," +
        "org.springdoc.core.configuration.SpringDocHateoasConfiguration"
})
@AutoConfigureMockMvc
@DisplayNameGeneration(ReplaceCamelCase.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class CatTest {

  MockMvcTester mockMvcTester;

  // Кратность трём
  private static final Condition<Integer> THREE_TIMES = new Condition<>(
      x -> x != 0 && x % 3 == 0,
      "three times");

  @Test
  @SneakyThrows
  @DisplayName("Cats is accessible via REST")
  void catsIsAccessibleViaRestTest() {
    mockMvcTester.get()
                 .uri("/cats")
                 .with(user("admin").roles("ADMIN"))
                 .assertThat()
                 .hasStatusOk()
                 .hasContentType("application/vnd.hal+json")
                 .bodyJson()
                 .hasPathSatisfying("$.page.totalElements", provider ->
                     provider.assertThat().convertTo(int.class).is(THREE_TIMES));
  }
}
