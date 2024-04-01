package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import java.util.Arrays;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;

@SuppressWarnings({"java:S2116", "java:S125"})

@ExtensionMethod(value = {
    Arrays.class,
}, suppressBaseMethods = false)
@UtilityClass
public class AddExtensionsDemo {
  int[] getSortedArray(int... ints) {
    val result = ints.clone();
    result.sort();
  //Arrays.sort(result);
    return result;
  }
}
