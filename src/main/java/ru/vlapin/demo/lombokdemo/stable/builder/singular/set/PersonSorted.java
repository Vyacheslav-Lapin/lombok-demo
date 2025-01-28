package ru.vlapin.demo.lombokdemo.stable.builder.singular.set;

import static lombok.AccessLevel.*;

import com.google.common.collect.ImmutableSortedSet;
import java.util.SortedSet;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@SuppressWarnings({"java:S4968", "java:S1117"})
//@Builder
public record PersonSorted(
//  @Singular
  SortedSet<String> contacts) {

  @Setter
  @ToString
  @Accessors(fluent = true)
  @FieldDefaults(level = PRIVATE)
  @NoArgsConstructor(access = PACKAGE)
  public static class PersonSortedBuilder {
    ImmutableSortedSet.Builder<String> contacts;

    public PersonSortedBuilder contact(String contact) {
      if (this.contacts == null) this.contacts = ImmutableSortedSet.naturalOrder();
      this.contacts.add(contact);
      return this;
    }

    public PersonSortedBuilder contacts(Iterable<? extends String> contacts) {
      if (contacts == null) {
        throw new NullPointerException("contacts cannot be null");
      }
      if (this.contacts == null) this.contacts = ImmutableSortedSet.naturalOrder();
      this.contacts.addAll(contacts);
      return this;
    }

    public PersonSortedBuilder clearContacts() {
      this.contacts = null;
      return this;
    }

    public PersonSorted build() {
      SortedSet<String> contacts = this.contacts == null ? ImmutableSortedSet.of() : this.contacts.build();
      return new PersonSorted(contacts);
    }
  }

  public static PersonSortedBuilder builder() {
    return new PersonSortedBuilder();
  }
}
