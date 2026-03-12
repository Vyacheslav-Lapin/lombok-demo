package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.jetbrains.annotations.Nullable;

@ExtensionMethod(suppressBaseMethods = false, value = {
    StringExtensions.class,
    ObjectExtensions.class,
    AddExtensionsDemo.class,
})

@RequiredArgsConstructor(staticName = "AddCustomExtensionsDemo")
public class AddCustomExtensionsDemo {

  String defaultName;

  @SuppressWarnings({"java:S2259", "java:S125", "SameParameterValue", "DataFlowIssue"})
  String titled(@Nullable String name) {
    return name.orIfNull(defaultName)
               .toTitleCase()
               .greetings();
//    return AddExtensionsDemo.greetings(
//        StringExtensions.toTitleCase(
//            ObjectExtensions.orIfNull(
//                name,
//                defaultName)));
  }
}
