package ru.vlapin.demo.lombokdemo.common;

import lombok.experimental.UtilityClass;
import org.hibernate.proxy.HibernateProxy;

@UtilityClass
public class HibernateUtils {

  public Class<?> persistentClass(Object object) {
    return object instanceof HibernateProxy proxy ?
        proxy.getHibernateLazyInitializer().getPersistentClass()
        : object.getClass();
  }
}
