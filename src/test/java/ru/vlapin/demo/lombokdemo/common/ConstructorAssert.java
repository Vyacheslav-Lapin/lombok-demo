package ru.vlapin.demo.lombokdemo.common;

import org.assertj.core.api.AbstractAssert;

/**
 * ConstructorAssert.
 */
public class ConstructorAssert extends AbstractAssert<ConstructorAssert, Class<?>> {
  protected ConstructorAssert(Class<?> aClass, Class<?> selfType) {
    super(aClass, selfType);
  }


}
