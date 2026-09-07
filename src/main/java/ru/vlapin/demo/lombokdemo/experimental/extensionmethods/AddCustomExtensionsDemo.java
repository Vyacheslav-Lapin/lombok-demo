package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.jetbrains.annotations.Nullable;

/**
 * blah-blah-blah...
 */

@ExtensionMethod(suppressBaseMethods = false, value = {
    ru.vlapin.demo.lombokdemo.common.ScopeFunctions.class,
    StringExtensions.class,
    AddExtensionsDemo.class,
})

@RequiredArgsConstructor(staticName = "AddCustomExtensionsDemo")
public class AddCustomExtensionsDemo {

  String defaultName;

  @SuppressWarnings({"java:S2259", "java:S125", "SameParameterValue"})
  public String titled(@Nullable String name) {
    return name.orIfNull(defaultName)
               .toTitleCase()
               .greetings()
        ;
  }
}
