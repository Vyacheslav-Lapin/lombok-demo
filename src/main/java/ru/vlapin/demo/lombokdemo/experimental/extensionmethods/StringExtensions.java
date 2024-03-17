package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;

@UtilityClass
public class StringExtensions {

  @Contract(pure = true)
  public String toTitleCase(String that) {
    return that.isEmpty() ? that :
               "%s%s".formatted(
                   Character.toTitleCase(that.charAt(0)),
                   that.substring(1).toLowerCase());
  }
}
