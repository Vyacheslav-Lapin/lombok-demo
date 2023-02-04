package ru.vlapin.demo.lombokdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Main.
 *
 * @author Vyacheslav Lapin
 */
public class Main {

  @SuppressWarnings({"java:S125", "java:S106"})
  public static void main(String... __) throws IOException {
    InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
    BufferedReader in = new BufferedReader(reader);
    String line;
    while ((line = in.readLine()) != null) {
      // System.out.println(line);
      System.out.println(get(Integer.parseInt(line)));
    }
  }

  private static int get(int i, int... ints) {
    if (ints.length == 0) {
      return get(i, 0);
    }

    if (i < ints.length) {
      return ints[i];
    }

    int[] ints2 = new int[ints.length * 2];
    System.arraycopy(ints, 0, ints2, 0, ints.length);
    for (int g = 0; g < ints.length; g++) {
      ints2[ints.length + g] = switch (ints[g]) {
        case 0 -> 1;
        case 1 -> 2;
        default -> 0;
      };
      if (i == g) {
        return ints2[ints.length + g];
      }
    }

    return get(i, ints2);
  }
}
