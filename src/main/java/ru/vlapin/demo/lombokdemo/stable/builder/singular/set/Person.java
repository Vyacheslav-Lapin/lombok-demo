package ru.vlapin.demo.lombokdemo.stable.builder.singular.set;

import java.util.Set;
import lombok.Builder;
import lombok.Singular;

@Builder
public record Person(
  @Singular Set<String> contacts) {

  // Реализация метода builder() — без изменений
//public static Person.PersonBuilder builder() {
//  return new Person.PersonBuilder();
//}

//@Setter @ToString @FieldDefaults(level = PRIVATE)
//@Accessors(fluent = true) @NoArgsConstructor(access = PACKAGE)
//public static class PersonBuilder {

//  ArrayList<String> contacts;

    // Реализации методов contact, contacts и clearContacts — без изменений
//  public Person.PersonBuilder contact(String contact) {
//    if (this.contacts == null) {
//      this.contacts = new ArrayList<>();
//    }
//    this.contacts.add(contact);
//    return this;
//  }

//  public Person.PersonBuilder contacts(@NonNull Collection<? extends String> contacts) {
//    if (this.contacts == null) {
//      this.contacts = new ArrayList<>();
//    }
//    this.contacts.addAll(contacts);
//    return this;
//  }

//  public Person.PersonBuilder clearContacts() {
//    if (this.contacts != null) {
//      this.contacts.clear();
//    }
//    return this;
//  }

//  public Person build() {
//    Set<String> contacts = switch (this.contacts == null ? 0 : this.contacts.size()) {
//      case 0 -> Collections.emptySet();
//      case 1 -> Collections.singleton(this.contacts.getFirst());
//      default -> {
//        contacts = new LinkedHashSet<>(
//            this.contacts.size() < 1_073_741_824
//                ? 1 + this.contacts.size() + (this.contacts.size() - 3) / 3
//                : Integer.MAX_VALUE);
//        contacts.addAll(this.contacts);
//        yield Collections.unmodifiableSet(contacts);
//      }
//    };
//    return new Person(contacts);
//  }
//}
}
