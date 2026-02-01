package ru.vlapin.demo.lombokdemo.common;

import static io.vavr.API.*;

import io.vavr.*;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TupleUtils {

  // region toTuple
  public <T> Tuple1<T> toTuple1(T $this) {
    return Tuple($this);
  }

  public <T> Tuple2<T, T> toTuple2(T $this) {
    return Tuple($this, $this);
  }

  public <T> Tuple3<T, T, T> toTuple3(T $this) {
    return Tuple($this, $this, $this);
  }

  public <T> Tuple4<T, T, T, T> toTuple4(T $this) {
    return Tuple($this, $this, $this, $this);
  }

  public <T> Tuple5<T, T, T, T, T> toTuple5(T $this) {
    return Tuple($this, $this, $this, $this, $this);
  }

  public <T> Tuple6<T, T, T, T, T, T> toTuple6(T $this) {
    return Tuple($this, $this, $this, $this, $this, $this);
  }

  public <T> Tuple7<T, T, T, T, T, T, T> toTuple7(T $this) {
    return Tuple($this, $this, $this, $this, $this, $this, $this);
  }

  public <T> Tuple8<T, T, T, T, T, T, T, T> toTuple8(T $this) {
    return Tuple($this, $this, $this, $this, $this, $this, $this, $this);
  }
  // endregion

  //region tupleWith
  public <T1, T2> Tuple2<T1, T2> tupleWith(T1 $this, T2 t2) {
    return Tuple($this, t2);
  }

  public <T1, T2, T3> Tuple3<T1, T2, T3> tupleWith(T1 $this, T2 t2, T3 t3) {
    return Tuple($this, t2, t3);
  }

  public <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> tupleWith(T1 $this, T2 t2, T3 t3, T4 t4) {
    return Tuple($this, t2, t3, t4);
  }

  public <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> tupleWith(T1 $this, T2 t2, T3 t3, T4 t4, T5 t5) {
    return Tuple($this, t2, t3, t4, t5);
  }

  public <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> tupleWith(T1 $this, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
    return Tuple($this, t2, t3, t4, t5, t6);
  }

  public <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7> tupleWith(T1 $this, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
    return Tuple($this, t2, t3, t4, t5, t6, t7);
  }

  @SuppressWarnings({"unused", "java:S107"})
  public <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> tupleWith(T1 $this, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6,
                                                                                           T7 t7, T8 t8) {
    return Tuple($this, t2, t3, t4, t5, t6, t7, t8);
  }
  //endregion

  //region applyChecked
  public <T1, R> R applyChecked(Tuple1<? extends T1> $this,
                                CheckedFunction1<? super T1, ? extends R> f) {
    return $this.apply(f.unchecked());
  }

  public <T1, T2, R> R applyChecked(Tuple2<? extends T1, ? extends T2> $this,
                                    CheckedFunction2<? super T1, ? super T2, ? extends R> f) {
    return $this.apply(f.unchecked());
  }

  public <T1, T2, T3, R> R applyChecked(Tuple3<? extends T1, ? extends T2, ? extends T3> $this,
                                        CheckedFunction3<? super T1, ? super T2, ? super T3, ? extends R> f) {
    return $this.apply(f.unchecked());
  }

  public <T1, T2, T3, T4, R> R applyChecked(Tuple4<? extends T1, ? extends T2, ? extends T3, ? extends T4> $this,
                                            CheckedFunction4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> f) {
    return $this.apply(f.unchecked());
  }

  public <T1, T2, T3, T4, T5, R> R applyChecked(Tuple5<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5> $this,
                                                CheckedFunction5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> f) {
    return $this.apply(f.unchecked());
  }

  public <T1, T2, T3, T4, T5, T6, R> R applyChecked(
      Tuple6<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5, ? extends T6> $this,
      CheckedFunction6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> f) {
    return $this.apply(f.unchecked());
  }

  public <T1, T2, T3, T4, T5, T6, T7, R> R applyChecked(
      Tuple7<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5, ? extends T6, ? extends T7> $this,
      CheckedFunction7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> f) {
    return $this.apply(f.unchecked());
  }

  public <T1, T2, T3, T4, T5, T6, T7, T8, R> R applyChecked(
      Tuple8<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5, ? extends T6, ? extends T7, ? extends T8> $this,
      CheckedFunction8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> f) {
    return $this.apply(f.unchecked());
  }
  //endregion
}
