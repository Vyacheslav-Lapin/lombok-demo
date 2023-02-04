package ru.vlapin.demo.lombokdemo.experimental.helper;

import lombok.val;

public class HelperExample {
  int someMethod(@SuppressWarnings("SameParameterValue") int arg1) {
    int localVar = 5;

    // @Helper // didn't work with IDEA Lombok plugin v.223.8214.52 :(
    //noinspection SameParameterValue
    class Helpers {
      int helperMethod(int arg) {
        return arg + arg1 + localVar;
      }
    }
    val $Helpers = new Helpers(); // should be removed after fix

    return $Helpers.helperMethod(10);
    // return helperMethod(10); // error - didn't work with IDEA Lombok plugin v.223.8214.52 :(
  }
}
