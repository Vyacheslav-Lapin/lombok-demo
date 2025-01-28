package ru.vlapin.demo.lombokdemo.stable.builder.singular.list;

import java.util.List;
import lombok.Builder;
import lombok.Singular;

@Builder
public record PersonWithChildren(
  @Singular List<String> children) {

//@Setter @ToString @NoArgsConstructor
//@Accessors(fluent = true) @FieldDefaults(level = PRIVATE)
//public static class PersonBuilder {
//  ArrayList<String> children;

//  public PersonBuilder child(String child) {
//    if (children == null) children = new ArrayList<>();
//    children.add(child);
//    return this;
//  }

//  public PersonBuilder children(@NonNull Collection<? extends String> children) {
//    if (this.children == null) this.children = new ArrayList<>();
//    this.children.addAll(children);
//    return this;
//  }

//  public PersonBuilder clearContacts() { if (children != null) children.clear(); return this; }

//  public Person build() {
//    List<String> children = switch (this.children == null ? 0 : this.children.size()) {
//      case 0 -> Collections.emptyList();
//      case 1 -> Collections.singletonList(this.children.getFirst());
//      default -> Collections.unmodifiableList(new ArrayList<>(this.children)); };
//    return new Person(children);
//  }
//}

//public static PersonBuilder builder() { return new PersonBuilder(); }
}
