package ru.vlapin.demo.lombokdemo.stable.builder.singular.list;

import static lombok.AccessLevel.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@SuppressWarnings({"java:S4968", "java:S1117", "java:S2259", "Java9CollectionFactory", "DuplicatedCode"})
//@SuppressWarnings("java:S125")
//@Builder
public record PersonIgnoreNull(
//  @Singular(ignoreNullCollections = true)
    List<String> contacts) {

  public static PersonIgnoreNullBuilder builder() {
    return new PersonIgnoreNullBuilder();
  }

  @Setter
  @ToString
  @Accessors(fluent = true)
  @FieldDefaults(level = PRIVATE)
  @NoArgsConstructor(access = PACKAGE)
  public static class PersonIgnoreNullBuilder {

    ArrayList<String> contacts;

    public PersonIgnoreNullBuilder contact(String contact) {
      if (contacts == null) {
        contacts = new ArrayList<>();
      }
      contacts.add(contact);
      return this;
    }

    public PersonIgnoreNullBuilder contacts(Collection<? extends String> contacts) {
      if (contacts != null) {
        if (this.contacts == null) {
          this.contacts = new ArrayList<>();
        }
        this.contacts.addAll(contacts);
      }
      return this;
    }

    // Реализации методов clearContacts() и build() — без изменений...
    public PersonIgnoreNullBuilder clearContacts() {
      if (contacts != null) {
        contacts.clear();
      }
      return this;
    }

    public PersonIgnoreNull build() {
      List<String> contacts = switch (this.contacts == null ? 0 : this.contacts.size()) {
        case 0 -> Collections.emptyList();
        case 1 -> Collections.singletonList(this.contacts.getFirst());
        default -> Collections.unmodifiableList(new ArrayList<>(this.contacts));
      };
      return new PersonIgnoreNull(contacts);
    }
  }
}
