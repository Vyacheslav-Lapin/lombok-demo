package ru.vlapin.demo.lombokdemo.experimental.field.defaults.config;

import lombok.experimental.PackagePrivate;

/**
 * LevelPrivateExample4.
 */
@SuppressWarnings({"java:S1104", "unused", "NotNullFieldNotInitialized"})
public class LevelPrivateExample4 {
  /*private*/ int x;
  protected int y;
  public int z;
  @PackagePrivate
  /*package-private*/ String s;
}
