package ru.vlapin.demo.lombokdemo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * ConferenceUser.
 *
 * @see <a href="https://youtu.be/TytSz7u1xQ8">"Test-Driven Security" by Eleftheria Stain-Kousathana, SpringOne, 2021</a>
 */
@Data
@RequiredArgsConstructor
public class ConferenceUser {
  String username;
  String password;
  List<String> submissions;
  boolean speaker;
  boolean admin;

  protected ConferenceUser(ConferenceUser user) {
    username = user.username;
    password = user.password;
    submissions = user.submissions;
    speaker = user.speaker;
    admin = user.admin;
  }
}
