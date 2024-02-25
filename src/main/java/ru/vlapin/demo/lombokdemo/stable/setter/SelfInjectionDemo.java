package ru.vlapin.demo.lombokdemo.stable.setter;

import static lombok.AccessLevel.*;

import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public interface SelfInjectionDemo {
  String method(@NotNull Integer x);
  void method();
}

//@Component
class SelfInjectionDemoImpl {

  @Setter(value = PRIVATE, onMethod_ = {@Autowired, @Lazy})
  SelfInjectionDemo selfInjectionDemo;

  public String method(Integer x) {
//    return STR."\{x}";

    //noinspection StringTemplateMigration
    return "" + x;
  }

  public void method() {
    System.out.println(selfInjectionDemo.method(5));
  }
}
