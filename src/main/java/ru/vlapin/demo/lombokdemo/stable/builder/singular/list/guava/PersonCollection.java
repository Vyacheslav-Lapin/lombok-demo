package ru.vlapin.demo.lombokdemo.stable.builder.singular.list.guava;

import static lombok.AccessLevel.*;

import com.google.common.collect.ImmutableList;
import java.util.Collection;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

//@Builder
public record PersonCollection(
//    @Singular
    Collection<String> contacts) {

  public static PersonCollectionBuilder builder() {
    return new PersonCollectionBuilder();
  }

  @Setter
  @ToString
  @NoArgsConstructor(access = PACKAGE)
  @Accessors(fluent = true)
  @FieldDefaults(level = PRIVATE)
  public static class PersonCollectionBuilder {

    ImmutableList.Builder<String> contacts;

    public PersonCollection.PersonCollectionBuilder contact(String contact) {
      if (this.contacts == null) {
        this.contacts = ImmutableList.builder();
      }
      this.contacts.add(contact);
      return this;
    }

    public PersonCollection.PersonCollectionBuilder contacts(Iterable<? extends String> contacts) {
      if (contacts == null) {
        throw new NullPointerException("contacts cannot be null");
      }
      if (this.contacts == null) {
        this.contacts = ImmutableList.builder();
      }
      this.contacts.addAll(contacts);
      return this;
    }

    public PersonCollection.PersonCollectionBuilder clearContacts() {
      contacts = null;
      return this;
    }

    @SuppressWarnings("java:S1117")
    public PersonCollection build() {
      Collection<String> contacts = this.contacts == null ? ImmutableList.of() : this.contacts.build();
      return new PersonCollection(contacts);
    }
  }
}
