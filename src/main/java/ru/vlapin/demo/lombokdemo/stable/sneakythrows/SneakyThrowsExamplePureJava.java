package ru.vlapin.demo.lombokdemo.stable.sneakythrows;

import static java.nio.charset.StandardCharsets.*;

import lombok.Lombok;

@SuppressWarnings("unused")
public class SneakyThrowsExamplePureJava implements Runnable {

  public String utf8ToString(byte[] bytes) {
    return new String(bytes, UTF_8);
  }

  public void run() {
    try {
      throw new Throwable();
    } catch (Throwable t) {
      throw Lombok.sneakyThrow(t);
    }
  }
}
