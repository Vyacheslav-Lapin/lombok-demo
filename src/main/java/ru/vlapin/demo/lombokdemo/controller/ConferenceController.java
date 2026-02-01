package ru.vlapin.demo.lombokdemo.controller;

import static org.springframework.http.ResponseEntity.*;

import java.util.List;
import lombok.SneakyThrows;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.vlapin.demo.lombokdemo.model.ConferenceUser;

/**
 * ConferenceController.
 *
 * @see <a href="https://youtu.be/TytSz7u1xQ8">"Test-Driven Security" by Eleftheria Stain-Kousathana, SpringOne, 2021</a>
 */
@RestController
public class ConferenceController {

  @NonFinal
  String aboutConference = "Join us online September 1-2!";

  @NotNull
  @Contract(pure = true)
  @GetMapping(path = "/about")
  public ResponseEntity<@NotNull String> getAbout() {
    return ok(aboutConference);
  }

  @PostMapping("/about")
  public ResponseEntity<Void> updateAbout(@RequestBody String updatedAbout) {
    aboutConference = updatedAbout;
    return noContent().build();
  }

  @NotNull
  @SneakyThrows
  @Contract(pure = true)
  @GetMapping("greetings")
  public ResponseEntity<@NotNull String> greetings(
      @SuppressWarnings("SpringElInspection")
      @AuthenticationPrincipal(expression = "username") String username) {
    return ok("Hello, %s!".formatted(username));
  }

  @NotNull
  @SneakyThrows
  @Contract(pure = true)
  @GetMapping("/submissions")
  public ResponseEntity<@NotNull List<String>> submissions(@AuthenticationPrincipal ConferenceUser user) {
    return ok(user.getSubmissions());
  }
}
