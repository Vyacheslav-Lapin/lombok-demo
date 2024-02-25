package ru.vlapin.demo.lombokdemo.model;

import java.util.List;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * ConferenceUser.
 *
 * @see <a href="https://youtu.be/TytSz7u1xQ8">"Test-Driven Security" by Eleftheria Stain-Kousathana, SpringOne, 2021</a>
 */
@Data
public class ConferenceUser {
  String username;
  String password;
  List<String> submissions;
  boolean speaker;
  boolean admin;

  @Tolerate
  protected ConferenceUser(ConferenceUser user) {
    username = user.username;
    password = user.password;
    submissions = user.submissions;
    speaker = user.speaker;
    admin = user.admin;
  }
}
