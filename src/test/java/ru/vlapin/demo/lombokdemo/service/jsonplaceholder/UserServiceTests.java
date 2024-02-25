package ru.vlapin.demo.lombokdemo.service.jsonplaceholder;

import static org.assertj.core.api.Assertions.*;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpEntity;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.api.UserApiClient;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.model.Address;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.model.Geo;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.model.User;

@SpringBootTest
@Testcontainers
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class UserServiceTests {

  @Container
  @ServiceConnection
  @SuppressWarnings("unused")
  static PostgreSQLContainer<?> postgreSQLContainer =
      new PostgreSQLContainer<>("postgres:latest");

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
