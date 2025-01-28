package ru.vlapin.demo.lombokdemo.stable.builder.singular.list;

import java.util.List;
import lombok.Builder;
import lombok.Singular;

@Builder
public record PersonSingle(
  @Singular("contactEntry") List<String> contact) {

//@Setter @ToString @NoArgsConstructor(access = PACKAGE)
//@Accessors(fluent = true) @FieldDefaults(level = PRIVATE)
//public static class PersonSingleBuilder {
//  private ArrayList<String> contact;

//  public PersonSingleBuilder contactEntry(String contactEntry) {
//    if (contact == null) contact = new ArrayList<>();
//    this.contact.add(contactEntry);
//    return this;
//  }

//  public PersonSingleBuilder contact(@NonNull Collection<? extends String> contact) {
//    if (this.contact == null) this.contact = new ArrayList<>();
//    this.contact.addAll(contact);
//    return this;
//  }

//  public PersonSingleBuilder clearContact() { if (contact != null) contact.clear(); return this;}

//  public PersonSingle build() {
//    List<String> contact = switch (this.contact == null ? 0 : this.contact.size()) {
//      case 0 -> Collections.emptyList();
//      case 1 -> Collections.singletonList(this.contact.getFirst());
//      default -> Collections.unmodifiableList(new ArrayList<>(this.contact));
//    };
//    return new PersonSingle(contact);
//  }
//}

//public static PersonSingleBuilder builder() { return new PersonSingleBuilder(); }
}
