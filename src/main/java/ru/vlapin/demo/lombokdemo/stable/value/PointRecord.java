package ru.vlapin.demo.lombokdemo.stable.value;

import java.io.Closeable;
import java.io.IOException;

/**
 * Point.
 */
//@SuppressWarnings({"java:S125", "unused"})

//class ColoredPoint extends PointRecord {
//  public ColoredPoint(int x, int y) {
//    super(x, y);
//  }
//}

//public /*final*/ class PointRecord {
@SuppressWarnings("java:S125")
public record PointRecord (
    int x,
    int y) implements Closeable {

  @Override
  public void close() throws IOException {
    //...
  }

//private final int x;
//private final int y;
  public static int z;

//public PointRecord(int x, int y) {
//  this.x = x;
//  this.y = y;
//}

//public int x() { return x; }
//public int y() { return y; }

//@Override public boolean equals(Object obj) {
//  return obj == this
//         || obj != null
//            && obj.getClass() == getClass()
//            && x == ((PointRecord) obj).x
//            && y == ((PointRecord) obj).y;
//}

//@Override
//public int hashCode() { return Objects.hash(x, y); }

//@Override
//public String toString() {
//  return "Point[x=" + x + ", y=" + y + ']';
//}
}
