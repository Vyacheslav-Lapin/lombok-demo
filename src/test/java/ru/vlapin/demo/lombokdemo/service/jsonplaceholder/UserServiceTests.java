package ru.vlapin.demo.lombokdemo.service.jsonplaceholder;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vlapin.demo.lombokdemo.model.jsonplaceholder.Address;
import ru.vlapin.demo.lombokdemo.model.jsonplaceholder.Geo;
import ru.vlapin.demo.lombokdemo.model.jsonplaceholder.User;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class UserServiceTests {

  UserService userService;

  @Test
  @SneakyThrows
  @DisplayName("User service works correctly")
  void userServiceWorksCorrectlyTest() {
    assertThat(userService.all()).isNotNull()
        .isNotEmpty()
        .hasSize(10);

    assertThat(userService.findById(1L)).isNotNull()
        .extracting(User::getAddress)
        .extracting(Address::getGeo)
        .extracting(Geo::getLatitude, Geo::getLongitude)
        .contains(-37.3159, 81.1496);
  }
}
