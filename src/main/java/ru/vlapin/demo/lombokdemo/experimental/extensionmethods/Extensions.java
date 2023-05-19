package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

@UtilityClass
public class Extensions {

  @Contract(value = "null, _ -> param2; !null, _ -> param1", pure = true)
  public <T> T getIfNull(@Nullable T that, T ifNull) {
    return that == null ? ifNull : that;
  }

  @Contract(pure = true)
  public String toTitleCase(String that) {
    return that.isEmpty() ? that :
               "%s%s".formatted(
                   Character.toTitleCase(that.charAt(0)),
                   that.substring(1).toLowerCase());
  }
}
