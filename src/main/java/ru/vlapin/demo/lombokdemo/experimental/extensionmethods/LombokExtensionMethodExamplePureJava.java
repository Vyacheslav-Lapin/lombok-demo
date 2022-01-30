package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import java.util.Arrays;

@SuppressWarnings("unused")
//@ExtensionMethod({Arrays.class, Extensions.class})
public class LombokExtensionMethodExamplePureJava {

  String useExtensionMethods() {
    String iAmNull = null;

    // return iAmNull.or("hELlO, WORlD!".toTitleCase());
    return Extensions.or(iAmNull, Extensions.toTitleCase("hELlO, WORlD!"));
  }

  public final int[] getSortedArray() {
    int[] intarray = {5, 3, 8, 2};

    // intarray.sort();
    Arrays.sort(intarray);

    return intarray;
  }
}
