package ru.vlapin.demo.lombokdemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import java.util.Objects;
import java.util.UUID;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import static lombok.AccessLevel.*;

@Entity
@Getter
@ToString
@Setter(PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor
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
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Cat cat = (Cat) o;
    return id != null && Objects.equals(id, cat.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
