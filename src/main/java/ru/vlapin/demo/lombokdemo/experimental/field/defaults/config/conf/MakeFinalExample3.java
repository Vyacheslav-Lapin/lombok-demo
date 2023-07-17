package ru.vlapin.demo.lombokdemo.experimental.field.defaults.config.conf;

import lombok.experimental.NonFinal;

/**
 * MakeFinalExample2.
 */
@SuppressWarnings({"FieldMayBeFinal", "unused"})
public class MakeFinalExample3 {
  @NonFinal
  /*!final*/ int x = 0;
  /*final*/ int y = 0;
  /*final*/ String s = "";
}
