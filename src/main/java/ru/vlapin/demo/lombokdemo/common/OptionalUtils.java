package ru.vlapin.demo.lombokdemo.common;

import java.util.Optional;
import java.util.function.Consumer;
import lombok.experimental.UtilityClass;

/**
 * Utility class providing additional methods for working with {@link Optional}.
 * <p>
 * This class includes operations that enhance the functionality of {@link Optional},
 * such as performing actions on the present value or filtering based on type.
 */
@UtilityClass
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class OptionalUtils {

  /**
   * Performs the provided action if a value is present in the given {@link Optional}.
   * This method allows performing a side effect on the value without altering the optional.
   *
   * @param <T> the type of the value contained in the optional
   * @param $this the {@link Optional} to be processed
   * @param consumer the {@link Consumer} that will be applied to the value if present
   * @return the same {@link Optional} that was passed in, allowing further chaining
   */
  public <T> Optional<T> peek(Optional<T> $this, Consumer<? super T> consumer) {
    return $this.map(t -> {
      consumer.accept(t);
      return t;
    });
  }

  /**
   * Filters the given {@link Optional} by checking if its value is an instance of the specified class,
   * and if it is, casts the value to the specified type.
   *
   * @param <T> the target type to filter and cast to
   * @param $this the {@link Optional} to be filtered
   * @param clazz the {@link Class} object representing the target type
   * @return an {@link Optional} containing the value cast to the target type if it satisfies the type check,
   *         or an empty {@link Optional} if the value is not an instance of the specified type or if the optional is empty
   */
  public <T> Optional<T> filterIsInstance(Optional<?> $this, Class<? extends T> clazz) {
    return $this.filter(clazz::isInstance)
                .map(clazz::cast);
  }
}
