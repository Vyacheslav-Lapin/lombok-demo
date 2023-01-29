package ru.vlapin.demo.lombokdemo.stable.value;

/**
 * Point.
 *
 * @author Vyacheslav Lapin
 */
@SuppressWarnings({"java:S125", "unused"})
public record Point(int x, int y) {

  public Point {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException();
    }
  }
//  public Point(int x, int y) {
//    if (x < 0 || y < 0) {
//      throw new IllegalArgumentException();
//    }
//    this.x = x;
//    this.y = y;
//  }
//}
}
