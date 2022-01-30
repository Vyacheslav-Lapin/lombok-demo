package ru.vlapin.demo.lombokdemo.stable.sneakythrows;

import java.io.UnsupportedEncodingException;

import lombok.Lombok;

@SuppressWarnings("unused")
public class SneakyThrowsExamplePureJava implements Runnable {

  public String utf8ToString(byte[] bytes) {
    try {
      return new String(bytes, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw Lombok.sneakyThrow(e);
    }
  }

  public void run() {
    try {
      throw new Throwable();
    } catch (Throwable t) {
      throw Lombok.sneakyThrow(t);
    }
  }
}
