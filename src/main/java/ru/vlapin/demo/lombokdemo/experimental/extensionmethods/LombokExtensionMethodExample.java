package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import lombok.experimental.ExtensionMethod;

import java.util.Arrays;

@ExtensionMethod({
    Arrays.class,
    String.class,
    StringExtensions.class,
    ObjectExtensions.class,
})
public class LombokExtensionMethodExample {

  @SuppressWarnings({"java:S2259", "java:S125"})
  String useExtensionMethods() {
    //noinspection DataFlowIssue
    String iAmNull = null;

    return iAmNull.orIfNull("hELlO, WORlD!".toTitleCase());
    // return ObjectExtensions.getIfNull(iAmNull, Extensions.toTitleCase("hELlO, WORlD!"));
  }

  @SuppressWarnings("java:S125")
  public int[] getSortedArray() {
    int[] ints = {5, 3, 8, 2};

    ints.sort();
    // Arrays.sort(ints);

    return ints;
  }

  @SuppressWarnings("java:S2209")
  public String hw(int count) {
    // NOTE! Это не "formatter", а "format" - extension method
    return "Hello, %d World!".format(count);
  }
}
