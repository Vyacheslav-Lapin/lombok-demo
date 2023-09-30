package ru.vlapin.demo.lombokdemo.stable.value;

/**
 * PointValue.
 */
//@SuppressWarnings({"java:S125", "unused"})

@SuppressWarnings("java:S125")

@lombok.Value
//@Getter
//@ToString//(doNotUseGetters = true)
//@EqualsAndHashCode//(doNotUseGetters = true)
//@AllArgsConstructor
//@Accessors(fluent = true)
//@FieldDefaults(level = PRIVATE,
//               makeFinal = true)
//public /*final*/ class PointValue {
public class PointValue {
  int x;
  int y;
}
