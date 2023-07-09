package ru.vlapin.demo.lombokdemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ConferenceUser.
 *
 * @see <a href="https://youtu.be/TytSz7u1xQ8">"Test-Driven Security" by Eleftheria Stain-Kousathana, SpringOne, 2021</a>
 */
@Data
@NoArgsConstructor
public class ConferenceUser {
  String username;
  String password;
  List<String> submissions;
  boolean speaker;
  boolean admin;

  protected ConferenceUser(ConferenceUser user) {
    this.username = user.username;
    this.password = user.password;
    this.submissions = user.submissions;
    this.speaker = user.speaker;
    this.admin = user.admin;
  }
}
