package ru.vlapin.demo.lombokdemo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString.Include;
import lombok.experimental.ExtensionMethod;
import ru.vlapin.demo.lombokdemo.common.HibernateUtils;

import java.util.Objects;
import java.util.UUID;

import static ru.vlapin.demo.lombokdemo.common.HibernateUtils.*;

@SuppressWarnings({
    "JpaObjectClassSignatureInspection",
    "com.haulmont.jpb.LombokDataInspection",
    "java:S2097",
})

@Data
@Entity
@ExtensionMethod({
    HibernateUtils.class,
})
public class Cat {

  //region id and version
  @Id
  @Include
  @GeneratedValue
  @Column(updatable = false, nullable = false)
  UUID id;

  @Version int version;
  //endregion

  @NonNull String name;

  //region equals and hashCode
  @Override
  public final boolean equals(Object object) {
    return this == object
           || object != null
              && persistentClass(this) == object.persistentClass()
              && object instanceof Cat cat
              && Objects.equals(getId(), cat.getId());
  }

  @Override
  public final int hashCode() {
    return persistentClass(this).hashCode();
  }
  //endregion
}
