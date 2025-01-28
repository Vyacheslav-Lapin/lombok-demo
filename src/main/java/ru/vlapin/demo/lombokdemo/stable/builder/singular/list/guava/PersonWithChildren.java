package ru.vlapin.demo.lombokdemo.stable.builder.singular.list.guava;

import static lombok.AccessLevel.*;

import com.google.common.collect.ImmutableList;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("java:S125")
//@Builder
public record PersonWithChildren(
//@Singular
    List<String> children) {

  public static PersonWithChildrenBuilder builder() {
    return new PersonWithChildrenBuilder();
  }

  @Setter
  @ToString
  @Accessors(fluent = true)
  @NoArgsConstructor(access = PACKAGE)
  @FieldDefaults(level = PRIVATE)
  public static class PersonWithChildrenBuilder {

    ImmutableList.Builder<String> children;

    public PersonWithChildren.PersonWithChildrenBuilder child(String child) {
      if (this.children == null) {
        this.children = ImmutableList.builder();
      }
      this.children.add(child);
      return this;
    }

    public PersonWithChildren.PersonWithChildrenBuilder children(Iterable<? extends String> children) {
      if (children == null) {
        throw new NullPointerException("children cannot be null");
      }
      if (this.children == null) {
        this.children = ImmutableList.builder();
      }
      this.children.addAll(children);
      return this;
    }

    public PersonWithChildren.PersonWithChildrenBuilder clearChildren() {
      children = null;
      return this;
    }

    public PersonWithChildren build() {
      List<String> children = this.children == null ? ImmutableList.of() : this.children.build();
      return new PersonWithChildren(children);
    }
  }
}
