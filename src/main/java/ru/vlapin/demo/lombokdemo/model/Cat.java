package ru.vlapin.demo.lombokdemo.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode.Include;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

import static lombok.AccessLevel.*;

@Entity
@Getter
@ToString
@Setter(PRIVATE)
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
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
