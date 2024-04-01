package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import java.util.Arrays;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;

@SuppressWarnings({"java:S2116", "java:S125"})

@ExtensionMethod(Arrays.class)
@UtilityClass
public class ReplaceExtensionsDemo {
  public String demo(int... ints) {
    //noinspection ImplicitArrayToString
    return ints.toString();
  //return Arrays.toString(ints);
  }
}
