package ru.vlapin.demo.lombokdemo.experimental.field.defaults;

import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.*;

/**
 * ScopeExample.
 */
@SuppressWarnings({"unused", "NotNullFieldNotInitialized"})
@FieldDefaults(level = PRIVATE)
public class LevelPrivateExample1 {
  /*private*/ int x;

  /*private*/
  String s;
}
