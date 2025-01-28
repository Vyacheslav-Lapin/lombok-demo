package ru.vlapin.demo.lombokdemo.stable.builder.singular.list;

import static lombok.AccessLevel.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@SuppressWarnings({"java:S4968", "java:S1117", "java:S2259", "Java9CollectionFactory"})
//@SuppressWarnings("java:S125")
//@Builder
public record PersonCollection(
//  @Singular
    Collection<String> contacts) {

  public static PersonCollectionBuilder builder() {
    return new PersonCollectionBuilder();
  }

  @Setter
  @ToString
  @Accessors(fluent = true)
  @FieldDefaults(level = PRIVATE)
  @NoArgsConstructor(access = PACKAGE)
  public static class PersonCollectionBuilder {

    ArrayList<String> contacts;

    public PersonCollectionBuilder contact(String contact) {
      if (contacts == null) {
        contacts = new ArrayList<>();
      }
      contacts.add(contact);
      return this;
    }

    public PersonCollectionBuilder contacts(@NonNull Collection<? extends String> contacts) {
      if (this.contacts == null) {
        this.contacts = new ArrayList<>();
      }
      this.contacts.addAll(contacts);
      return this;
    }

    public PersonCollectionBuilder clearContacts() {
      if (contacts != null) {
        contacts.clear();
      }
      return this;
    }

    public PersonCollection build() {
      Collection<String> contacts = switch (this.contacts == null ? 0 : this.contacts.size()) {
        case 0 -> Collections.emptyList();
        case 1 -> Collections.singletonList(this.contacts.getFirst());
        default -> Collections.unmodifiableList(new ArrayList<>(this.contacts));
      };
      return new PersonCollection(contacts);
    }
  }
}
