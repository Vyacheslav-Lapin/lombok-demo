package ru.vlapin.demo.lombokdemo.stable.sneakythrows;

import java.io.UnsupportedEncodingException;

import lombok.SneakyThrows;

public class SneakyThrowsExampleWithLombok implements Runnable {

  @SneakyThrows(UnsupportedEncodingException.class)
  public String utf8ToString(byte[] bytes) {
    return new String(bytes, "UTF-8");
  }

  @Override
  @SneakyThrows
  public void run() {
    throw new Throwable();
  }
}
