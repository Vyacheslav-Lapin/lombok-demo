package ru.vlapin.demo.lombokdemo.service.jsonplaceholder;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.api.UserApiClient;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.model.Address;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.model.Geo;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.model.User;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class UserServiceTests {

  UserApiClient client;

  @Test
  @SneakyThrows
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
