package ru.vlapin.demo.lombokdemo.stable.builder.singular.list.guava;

import static lombok.AccessLevel.*;

import com.google.common.collect.ImmutableList;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("java:S125")
//@Builder
public record PersonIterable(
    //@Singular
    Iterable<String> contacts) {

  public static PersonIterableBuilder builder() {
    return new PersonIterableBuilder();
  }

  @Setter
  @ToString
  @Accessors(fluent = true)
  @FieldDefaults(level = PRIVATE)
  @NoArgsConstructor(access = PACKAGE)
  public static class PersonIterableBuilder {

    private ImmutableList.Builder<String> contacts;

    public PersonIterable.PersonIterableBuilder contact(String contact) {
      if (contacts == null) {
        contacts = ImmutableList.builder();
      }
      contacts.add(contact);
      return this;
    }

    public PersonIterable.PersonIterableBuilder contacts(Iterable<? extends String> contacts) {
      if (contacts == null) {
        throw new NullPointerException("contacts cannot be null");
      }
      if (this.contacts == null) {
        this.contacts = ImmutableList.builder();
      }
      this.contacts.addAll(contacts);
      return this;
    }

    public PersonIterable.PersonIterableBuilder clearContacts() {
      contacts = null;
      return this;
    }

    public PersonIterable build() {
      Iterable<String> contacts = this.contacts == null ? ImmutableList.of() : this.contacts.build();
      return new PersonIterable(contacts);
    }
  }
}
