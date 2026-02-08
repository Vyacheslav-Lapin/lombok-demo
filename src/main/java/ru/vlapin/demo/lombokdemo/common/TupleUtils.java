package ru.vlapin.demo.lombokdemo.common;

import static io.vavr.API.*;

import io.vavr.*;
import lombok.experimental.UtilityClass;

/**
 * Utility class for working with tuples.
 * Provides methods to create tuples of various sizes from objects,
 * combine objects into tuples, and apply functions to tuples with exception handling.
 */
@UtilityClass
public class TupleUtils {

  /**
   * Converts the given object into a {@link Tuple1}.
   *
   * @param $this the object to be converted into a single-element tuple
   * @return a {@link Tuple1} containing the given object
   */
  // region toTuple
  public <T> Tuple1<T> toTuple1(T $this) {
    return Tuple($this);
  }

  /**
   * Converts the given object into a {@link Tuple2}, where both elements of the tuple are the same object.
   *
   * @param $this the object to be converted into a two-element tuple
   * @return a {@link Tuple2} containing the given object as both elements
   */
  public <T> Tuple2<T, T> toTuple2(T $this) {
    return Tuple($this, $this);
  }

  /**
   * Converts the given object into a {@link Tuple3}, where all three elements of the tuple are the same object.
   *
   * @param $this the object to be converted into a three-element tuple
   * @return a {@link Tuple3} containing the given object as all three elements
   */
  public <T> Tuple3<T, T, T> toTuple3(T $this) {
    return Tuple($this, $this, $this);
  }

  /**
   * Converts the given object into a {@link Tuple4}, where all four elements of the tuple are the same object.
   *
   * @param $this the object to be converted into a four-element tuple
   * @return a {@link Tuple4} containing the given object as all four elements
   */
  public <T> Tuple4<T, T, T, T> toTuple4(T $this) {
    return Tuple($this, $this, $this, $this);
  }

  /**
   * Converts the given object into a {@link Tuple5}, where all five elements of the tuple are the same object.
   *
   * @param $this the object to be converted into a five-element tuple
   * @return a {@link Tuple5} containing the given object as all five elements
   */
  public <T> Tuple5<T, T, T, T, T> toTuple5(T $this) {
    return Tuple($this, $this, $this, $this, $this);
  }

  /**
   * Converts the given object into a {@link Tuple6}, where all six elements of the tuple are the same object.
   *
   * @param $this the object to be converted into a six-element tuple
   * @return a {@link Tuple6} containing the given object as all six elements
   */
  public <T> Tuple6<T, T, T, T, T, T> toTuple6(T $this) {
    return Tuple($this, $this, $this, $this, $this, $this);
  }

  /**
   * Converts the given object into a {@link Tuple7}, where all seven elements of the tuple
   * are the same object.
   *
   * @param $this the object to be converted into a seven-element tuple
   * @return a {@link Tuple7} containing the given object as all seven elements
   */
  public <T> Tuple7<T, T, T, T, T, T, T> toTuple7(T $this) {
    return Tuple($this, $this, $this, $this, $this, $this, $this);
  }

  /**
   * Converts the given object into a {@link Tuple8}, where all eight elements of the tuple are the same object.
   *
   * @param $this the object to be converted into an eight-element tuple
   * @return a {@link Tuple8} containing the given object as all eight elements
   */
  public <T> Tuple8<T, T, T, T, T, T, T, T> toTuple8(T $this) {
    return Tuple($this, $this, $this, $this, $this, $this, $this, $this);
  }
  // endregion

  /**
   * Creates a {@link Tuple2} from the given elements.
   *
   * @param $this the first element of the tuple
   * @param t2 the second element of the tuple
   * @return a {@link Tuple2} containing the provided elements
   */
  //region tupleWith
  public <T1, T2> Tuple2<T1, T2> tupleWith(T1 $this, T2 t2) {
    return Tuple($this, t2);
  }

  /**
   * Creates a {@link Tuple3} from the given elements.
   *
   * @param $this the first element to include in the tuple
   * @param t2 the second element to include in the tuple
   * @param t3 the third element to include in the tuple
   * @return a {@link Tuple3} containing the provided elements
   */
  public <T1, T2, T3> Tuple3<T1, T2, T3> tupleWith(T1 $this, T2 t2, T3 t3) {
    return Tuple($this, t2, t3);
  }

  /**
   * Creates a {@link Tuple4} from the given elements.
   *
   * @param $this the first element of the tuple
   * @param t2 the second element of the tuple
   * @param t3 the third element of the tuple
   * @param t4 the fourth element of the tuple
   * @return a {@link Tuple4} containing the provided elements
   */
  public <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> tupleWith(T1 $this, T2 t2, T3 t3, T4 t4) {
    return Tuple($this, t2, t3, t4);
  }

  /**
   * Creates a {@link Tuple5} from the given elements.
   *
   * @param $this the first element of the tuple
   * @param t2 the second element of the tuple
   * @param t3 the third element of the tuple
   * @param t4 the fourth element of the tuple
   * @param t5 the fifth element of the tuple
   * @return a {@link Tuple5} containing the provided elements
   */
  public <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> tupleWith(T1 $this, T2 t2, T3 t3, T4 t4, T5 t5) {
    return Tuple($this, t2, t3, t4, t5);
  }

  /**
   * Creates a {@link Tuple6} from the given elements.
   *
   * @param $this the first element of the tuple
   * @param t2 the second element of the tuple
   * @param t3 the third element of the tuple
   * @param t4 the fourth element of the tuple
   * @param t5 the fifth element of the tuple
   * @param t6 the sixth element of the tuple
   * @return a {@link Tuple6} containing the provided elements
   */
  public <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> tupleWith(T1 $this, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
    return Tuple($this, t2, t3, t4, t5, t6);
  }

  /**
   * Creates a {@link Tuple7} from the given elements.
   *
   * @param $this the first element of the tuple
   * @param t2 the second element of the tuple
   * @param t3 the third element of the tuple
   * @param t4 the fourth element of the tuple
   * @param t5 the fifth element of the tuple
   * @param t6 the sixth element of the tuple
   * @param t7 the seventh element of the tuple
   * @return a {@link Tuple7} containing the provided elements
   */
  public <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7> tupleWith(T1 $this, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
    return Tuple($this, t2, t3, t4, t5, t6, t7);
  }

  /**
   * Creates a {@link Tuple8} from the given elements.
   *
   * @param $this the first element of the tuple
   * @param t2 the second element of the tuple
   * @param t3 the third element of the tuple
   * @param t4 the fourth element of the tuple
   * @param t5 the fifth element of the tuple
   * @param t6 the sixth element of the tuple
   * @param t7 the seventh element of the tuple
   * @param t8 the eighth element of the tuple
   * @return a {@link Tuple8} containing the provided elements
   */
  @SuppressWarnings({"unused", "java:S107"})
  public <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> tupleWith(T1 $this, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6,
                                                                                           T7 t7, T8 t8) {
    return Tuple($this, t2, t3, t4, t5, t6, t7, t8);
  }
  //endregion

  /**
   * Applies a checked function to the given tuple and returns the result.
   *
   * @param $this The tuple containing the input value for the function.
   * @param f A checked function to be applied to the value within the tuple.
   * @param <T1> The type of the input element in the tuple.
   * @param <R> The type of the result produced by the function.
   * @return The result of applying the checked function to the value in the tuple.
   */
  //region applyChecked
  public <T1, R> R applyChecked(Tuple1<? extends T1> $this,
                                CheckedFunction1<? super T1, ? extends R> f) {
    return $this.apply(f.unchecked());
  }

  /**
   * Applies a checked function to the components of the provided tuple and returns the result.
   * The checked function is converted to an unchecked function during execution.
   *
   * @param $this the tuple containing the input arguments for the function
   * @param f the checked function to apply to the components of the tuple
   * @return the result of applying the checked function to the elements of the tuple
   */
  public <T1, T2, R> R applyChecked(Tuple2<? extends T1, ? extends T2> $this,
                                    CheckedFunction2<? super T1, ? super T2, ? extends R> f) {
    return $this.apply(f.unchecked());
  }

  /**
   * Applies a checked function to the components of the given tuple and returns the result.
   *
   * @param $this the tuple containing three elements of types T1, T2, and T3
   * @param f     the checked function to apply, which takes three arguments of types T1, T2, and T3 and returns a result of type R
   * @param <T1>  the type of the first element in the tuple
   * @param <T2>  the type of the second element in the tuple
   * @param <T3>  the type of the third element in the tuple
   * @param <R>   the type of the result produced by the function
   * @return the result of applying the function to the components of the tuple
   */
  public <T1, T2, T3, R> R applyChecked(Tuple3<? extends T1, ? extends T2, ? extends T3> $this,
                                        CheckedFunction3<? super T1, ? super T2, ? super T3, ? extends R> f) {
    return $this.apply(f.unchecked());
  }

  /**
   * Applies a checked function to the elements of a tuple and returns the result.
   *
   * @param $this the tuple containing the elements to be applied to the function
   * @param f the checked function to be applied to the tuple elements
   * @param <T1> the type of the first element in the tuple
   * @param <T2> the type of the second element in the tuple
   * @param <T3> the type of the third element in the tuple
   * @param <T4> the type of the fourth element in the tuple
   * @param <R> the type of the result returned by the function
   * @return the result obtained after applying the checked function to the tuple elements
   */
  public <T1, T2, T3, T4, R> R applyChecked(Tuple4<? extends T1, ? extends T2, ? extends T3, ? extends T4> $this,
                                            CheckedFunction4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> f) {
    return $this.apply(f.unchecked());
  }

  /**
   * Applies a {@link CheckedFunction5} to the elements of a {@link Tuple5}, propagating any checked exceptions as unchecked exceptions.
   *
   * @param <T1> the type of the first element in the tuple
   * @param <T2> the type of the second element in the tuple
   * @param <T3> the type of the third element in the tuple
   * @param <T4> the type of the fourth element in the tuple
   * @param <T5> the type of the fifth element in the tuple
   * @param <R> the return type of the function
   * @param $this the tuple containing the elements to which the function will be applied
   * @param f the checked function to be applied to the tuple elements
   * @return the result of applying the function to the tuple elements
   */
  public <T1, T2, T3, T4, T5, R> R applyChecked(Tuple5<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5> $this,
                                                CheckedFunction5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> f) {
    return $this.apply(f.unchecked());
  }

  /**
   * Applies a checked function to the elements of the given tuple and returns the result.
   *
   * @param <T1> the type of the first element in the tuple
   * @param <T2> the type of the second element in the tuple
   * @param <T3> the type of the third element in the tuple
   * @param <T4> the type of the fourth element in the tuple
   * @param <T5> the type of the fifth element in the tuple
   * @param <T6> the type of the sixth element in the tuple
   * @param <R> the return type of the function
   * @param $this the tuple containing the elements to which the function is applied
   * @param f the checked function to be applied to the tuple's elements
   * @return the result of applying the function to the tuple's elements
   */
  public <T1, T2, T3, T4, T5, T6, R> R applyChecked(
      Tuple6<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5, ? extends T6> $this,
      CheckedFunction6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> f) {
    return $this.apply(f.unchecked());
  }

  /**
   * Applies a given checked function to the elements of a tuple with seven components
   * and returns the result.
   *
   * @param <T1> the type of the first component in the tuple
   * @param <T2> the type of the second component in the tuple
   * @param <T3> the type of the third component in the tuple
   * @param <T4> the type of the fourth component in the tuple
   * @param <T5> the type of the fifth component in the tuple
   * @param <T6> the type of the sixth component in the tuple
   * @param <T7> the type of the seventh component in the tuple
   * @param <R> the type of the result of the function
   * @param $this the tuple with seven components whose elements will be applied to the function
   * @param f a checked function that accepts seven arguments and returns a result
   * @return the result of applying the given function to the elements of the tuple
   */
  public <T1, T2, T3, T4, T5, T6, T7, R> R applyChecked(
      Tuple7<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5, ? extends T6, ? extends T7> $this,
      CheckedFunction7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> f) {
    return $this.apply(f.unchecked());
  }

  /**
   * Applies a checked function to a tuple with eight components and returns the result.
   *
   * @param $this the tuple containing eight components to which the function will be applied
   * @param f the checked function to apply to the tuple's components
   * @param <T1> the type of the first component in the tuple
   * @param <T2> the type of the second component in the tuple
   * @param <T3> the type of the third component in the tuple
   * @param <T4> the type of the fourth component in the tuple
   * @param <T5> the type of the fifth component in the tuple
   * @param <T6> the type of the sixth component in the tuple
   * @param <T7> the type of the seventh component in the tuple
   * @param <T8> the type of the eighth component in the tuple
   * @param <R> the return type of the function
   * @return the result of applying the checked function to the components of the tuple
   */
  public <T1, T2, T3, T4, T5, T6, T7, T8, R> R applyChecked(
      Tuple8<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5, ? extends T6, ? extends T7, ? extends T8> $this,
      CheckedFunction8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> f) {
    return $this.apply(f.unchecked());
  }
  //endregion
}
