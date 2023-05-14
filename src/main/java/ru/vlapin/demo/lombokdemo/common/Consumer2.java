package ru.vlapin.demo.lombokdemo.common;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Represents a function with two arguments without return value.
 *
 * @param <T1> argument 1 of the function
 * @param <T2> argument 2 of the function
 * @author Vyacheslav Lapin
 */
@FunctionalInterface
public interface Consumer2<T1, T2> extends BiConsumer<T1, T2> {

  /**
   * The <a href="https://docs.oracle.com/javase/8/docs/api/index.html">serial version uid</a>.
   */
  @SuppressWarnings("unused")
  long serialVersionUID = 1L;

  /**
   * Creates a {@code Consumer2} based on
   * <ul>
   * <li><a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">method reference</a></li>
   * <li><a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">lambda expression</a></li>
   * </ul>
   * <p>
   * Examples:
   * <pre>{@code // using a lambda expression
   * Consumer2<Integer, Integer> add1 = Consumer2.of(i -> i + 1);
   *
   * // using a method reference (, e.g. Integer method(Integer i) { return i + 1; })
   * Consumer2<Integer, Integer> add2 = Consumer2.of(this::method);
   *
   * // using a lambda reference
   * Consumer2<Integer, Integer> add3 = Consumer2.of(add1::apply);
   * }</pre>
   * <p>
   * <strong>Caution:</strong> Reflection loses type information of lambda references.
   * <pre>{@code // type of a lambda expression
   * Type<?, ?> type1 = add1.getType(); // (Integer) -> Integer
   *
   * // type of a method reference
   * Type<?, ?> type2 = add2.getType(); // (Integer) -> Integer
   *
   * // type of a lambda reference
   * Type<?, ?> type3 = add3.getType(); // (Object) -> Object
   * }</pre>
   *
   * @param methodReference (typically) a method reference, e.g. {@code Type::method}
   * @param <T1>            1st argument
   * @param <T2>            2nd argument
   * @return a {@code Function2}
   */
  static <T1, T2> Consumer2<T1, T2> of(Consumer2<T1, T2> methodReference) {
    return methodReference;
  }

  /**
   * Narrows the given {@code Consumer2<? super T1, ? super T2>} to {@code Consumer2<T1, T2>}
   *
   * @param self A {@code Connsumer2}
   * @param <T1> 1st argument
   * @param <T2> 2nd argument
   * @return the given {@code self} instance as narrowed type {@code Function2<T1, T2>}
   */
  @SuppressWarnings("unchecked")
  static <T1, T2> Consumer2<T1, T2> narrow(Consumer2<? extends T1, ? extends T2> self) {
    return (Consumer2<T1, T2>) self;
  }

  static <T1, T2> Consumer2<T1, T2> from(BiConsumer<T1, T2> biConsumer) {
    return biConsumer::accept;
  }

  /**
   * Returns a reversed version of this consumer. This may be useful in a recursive context.
   *
   * @return a reversed consumer equivalent to this.
   */
  default Consumer2<T2, T1> reversed() {
    return (t2, t1) -> accept(t1, t2);
  }

  default Consumer<T2> accept(T1 t) {
    return t2 -> accept(t, t2);
  }
}
