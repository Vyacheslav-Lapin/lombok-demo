package ru.vlapin.demo.lombokdemo.service.jsonplaceholder;

import static org.assertj.core.api.Assertions.*;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.api.UserApiClient;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.model.Address;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.model.Geo;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.model.User;

@SpringBootTest(properties = {
    "spring.jackson.visibility.field=any",
    "spring.jackson.visibility.getter=any",
    "spring.jackson.visibility.setter=any",
    "spring.jackson.visibility.creator=any"
})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@DisplayNameGeneration(ReplaceCamelCase.class)
class UserServiceTests {

  UserApiClient client;

  @Test
  @Tag("integration")
  @DisplayName("User service works correctly")
  void userServiceWorksCorrectlyTest() {

    assertThat(client.users()).isNotNull()
            .extracting(HttpEntity::getBody).asList()
            .isNotEmpty()
            .hasSize(10);

    assertThat(client.pickUser(1)).isNotNull()
            .extracting(HttpEntity::getBody)
            .extracting(User::getAddress)
            .extracting(Address::getGeo)
            .extracting(Geo::getLat, Geo::getLng)
            .contains(-37.3159, 81.1496);
  }
}
