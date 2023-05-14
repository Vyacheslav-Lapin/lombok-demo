package ru.vlapin.demo.lombokdemo.controller.jsonplaceholder;

import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.api.UserApiClient;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.model.User;

import java.util.List;
import java.util.Objects;

@ExtensionMethod({
        Objects.class,
})
@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
@SuppressWarnings("java:S2259")
public class UserController {

  UserApiClient client;

  @GetMapping
  public List<User> all() {
    return client.users()
            .getBody()
            .requireNonNull();
  }

  @GetMapping("{id}")
  public User byId(@PathVariable Integer id) {
    return client.pickUser(id)
            .getBody()
            .requireNonNull();
  }
}
