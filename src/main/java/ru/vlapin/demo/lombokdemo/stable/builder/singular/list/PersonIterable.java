package ru.vlapin.demo.lombokdemo.stable.builder.singular.list;

import lombok.Builder;
import lombok.Singular;

@SuppressWarnings({"java:S125", "CommentedOutCode"})
@Builder
public record PersonIterable(
    @Singular
    Iterable<String> contacts) {

//public static PersonIterableBuilder builder() {
//  return new PersonIterableBuilder();
//}

//@Setter
//@ToString
//@Accessors(fluent = true)
//@FieldDefaults(level = PRIVATE)
//@NoArgsConstructor(access = PACKAGE)
//public static class PersonIterableBuilder {

//  ArrayList<String> contacts;

//  public PersonIterableBuilder contact(String contact) {
//    if (contacts == null) {
//      contacts = new ArrayList<>();
//    }
//    contacts.add(contact);
//    return this;
//  }

//  public PersonIterableBuilder contacts(@NonNull Collection<? extends String> contacts) {
//    if (this.contacts == null) {
//      this.contacts = new ArrayList<>();
//    }
//    this.contacts.addAll(contacts);
//    return this;
//  }

//  public PersonIterableBuilder clearContacts() {
//    if (contacts != null) {
//      contacts.clear();
//    }
//    return this;
//  }

//  @SuppressWarnings("java:S2259")
//  public PersonIterable build() {
//    Iterable<String> contacts = switch (this.contacts == null ? 0 : this.contacts.size()) {
//      case 0 -> java.util.Collections.emptyList();
//      case 1 -> java.util.Collections.singletonList(this.contacts.getFirst());
//      default -> java.util.Collections.unmodifiableList(new ArrayList<String>(this.contacts));
//    };
//    return new PersonIterable(contacts);
//  }
//}
}
