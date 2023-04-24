package ru.vlapin.demo.lombokdemo.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode.Include;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@SuppressWarnings({
        "JpaObjectClassSignatureInspection",
        "com.haulmont.jpb.LombokDataInspection",
})
public class Cat {

  @Id
  @Include
  @GeneratedValue
  @Column(updatable = false, nullable = false)
  UUID id;

  @Version int version;

  @NonNull String name;

  @Override
  public boolean equals(Object o) {
    return this == o || o != null &&
            Hibernate.getClass(this) == Hibernate.getClass(o)
            && id != null
            && o instanceof Cat cat
            && Objects.equals(id, cat.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
