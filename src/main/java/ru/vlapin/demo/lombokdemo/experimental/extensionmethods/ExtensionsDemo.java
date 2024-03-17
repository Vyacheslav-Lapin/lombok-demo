package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import java.util.Arrays;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;

@SuppressWarnings({"java:S2116", "java:S125"})

@ExtensionMethod(Arrays.class)
@UtilityClass
public class ExtensionsDemo {

  int[] getSortedArray(int... ints) {
    val result = ints.clone();
    result.sort();
  //Arrays.sort(result);
    return result;
  }

  public String demo(int... ints) {
    //noinspection ImplicitArrayToString
    return ints.toString();
  //return Arrays.toString(ints);
  }
}
