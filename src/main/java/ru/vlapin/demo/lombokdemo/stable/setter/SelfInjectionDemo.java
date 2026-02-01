package ru.vlapin.demo.lombokdemo.stable.setter;

import static lombok.AccessLevel.*;

import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

public interface SelfInjectionDemo {
  String method(@NotNull Integer x);
  void method();
}

@Component
class SelfInjectionDemoImpl implements SelfInjectionDemo {

  @Setter(value = PRIVATE, onMethod_ = {@Autowired, @Lazy})
  SelfInjectionDemo self;

  public String method(@NotNull Integer x) {
    return x.toString();
  }

  public void method() {
    System.out.println(self.method(5));
  }
}
