package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import org.jetbrains.annotations.Contract;

@SuppressWarnings("unused")
public interface ExtensionsInterface {

  @Contract(value = "null, _ -> param2; !null, _ -> param1", pure = true)
  static <T> T getIfNull(T that, T ifNull) {
    return that == null ? ifNull : that;
  }

  @Contract(pure = true)
  static String toTitleCase(String that) {
    return that.isEmpty() ? that :
               String.format("%s%s",
                             Character.toTitleCase(that.charAt(0)),
                             that.substring(1).toLowerCase());
  }
}
