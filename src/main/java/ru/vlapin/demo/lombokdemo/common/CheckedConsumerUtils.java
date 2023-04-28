package ru.vlapin.demo.lombokdemo.common;

import io.vavr.CheckedConsumer;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class CheckedConsumerUtils {

    /**
     * Returns a chained {@code CheckedConsumer} that first executes {@code before.accept(t)}
     * and then {@code this.accept(t)}, for a given {@code t} of type {@code T}.
     *
     * @param self the action that will be executed
     * @param before the action that will be executed before this action
     * @return a new {@code CheckedConsumer} that chains {@code before} and {@code this}
     * @throws NullPointerException if {@code before} is null
     */
    public <T> CheckedConsumer<T> compose(CheckedConsumer<T> self, CheckedConsumer<? super T> before) {
        Objects.requireNonNull(before, "after is null");
        return (T t) -> {
            before.accept(t);
            self.accept(t);
        };
    }
}
