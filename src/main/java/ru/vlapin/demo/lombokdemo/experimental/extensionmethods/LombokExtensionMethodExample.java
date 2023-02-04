package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import java.util.Arrays;
import lombok.experimental.ExtensionMethod;

@ExtensionMethod({
    Arrays.class,
    String.class,
    Extensions.class,
})
public class LombokExtensionMethodExample {

  @SuppressWarnings({"java:S2259", "java:S125"})
  String useExtensionMethods() {
    String iAmNull = null;

    return iAmNull.getIfNull("hELlO, WORlD!".toTitleCase());
    // return Extensions.getIfNull(iAmNull, Extensions.toTitleCase("hELlO, WORlD!"));
  }

  @SuppressWarnings("java:S125")
  public int[] getSortedArray() {
    int[] intarray = {5, 3, 8, 2};

    intarray.sort();
    // Arrays.sort(intarray);

    return intarray;
  }

  public String hw(int count) {
    return "Hello, %d World!".format(count);
  }
}
