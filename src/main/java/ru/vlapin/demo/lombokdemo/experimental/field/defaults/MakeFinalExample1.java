package ru.vlapin.demo.lombokdemo.experimental.field.defaults;

import lombok.experimental.FieldDefaults;

/**
 * MakeFinalExample1.
 */
@SuppressWarnings("unused")
@FieldDefaults(makeFinal = true)
public class MakeFinalExample1 {
  /*final*/ int x = 0;
  /*final*/ String s = "";
}
