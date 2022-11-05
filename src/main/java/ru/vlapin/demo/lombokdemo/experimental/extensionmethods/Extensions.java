package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class Extensions {

  @Contract(value = "null, _ -> param2; !null, _ -> param1", pure = true)
  public <T> T or(T that, @NotNull T ifNull) {
    return that == null ? ifNull : that;
  }

  public String toTitleCase(@NotNull String that) {
    return that.isEmpty() ? that :
               String.format("%s%s",
                             Character.toTitleCase(that.charAt(0)),
                             that.substring(1).toLowerCase());
  }
}
