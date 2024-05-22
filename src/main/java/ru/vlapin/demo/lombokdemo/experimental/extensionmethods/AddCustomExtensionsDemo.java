package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.jetbrains.annotations.Nullable;

@ExtensionMethod(value = {
    StringExtensions.class,
    ObjectExtensions.class,
    }, suppressBaseMethods = false)
@RequiredArgsConstructor(
    staticName = "AddCustomExtensionsDemo")
public class AddCustomExtensionsDemo {

  String defaultName;

  @SuppressWarnings({"java:S2259", "java:S125", "SameParameterValue"})
  String titled(@Nullable String name) {
  return name.orIfNull(defaultName)
              .toTitleCase();
//    return StringExtensions.toTitleCase(
//        ObjectExtensions.orIfNull(
//            name,
//            defaultName));
  }
}
