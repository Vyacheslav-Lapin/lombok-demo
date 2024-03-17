package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import java.util.function.Supplier;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

@UtilityClass
public class ObjectExtensions {

  @Contract(value = "null, _ -> param2; !null, _ -> param1", pure = true)
  public <T> T orIfNull(@Nullable T that, T ifNull) {
    return that == null ? ifNull : that;
  }

  @Contract(pure = true)
  public <T> T orIfNull(@Nullable T that, Supplier<T> ifNull) {
    return that == null ? ifNull.get() : that;
  }
}
