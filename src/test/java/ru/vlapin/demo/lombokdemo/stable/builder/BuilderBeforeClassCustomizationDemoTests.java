package ru.vlapin.demo.lombokdemo.stable.builder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.stable.builder.BuilderBeforeClassCustomizationDemo.FooCreator;

import static org.assertj.core.api.Assertions.*;

class BuilderBeforeClassCustomizationDemoTests {

  @Test
  @DisplayName("@Builder castomization works correctly")
  void builderCastomizationWorksCorrectlyTest() {
    FooCreator fooCreator = BuilderBeforeClassCustomizationDemo.creator();
    assertThat(fooCreator.withX(5)
                   .withB(true)
                   .withS("lorem").create())
        .extracting("x", "s", "b")
        .contains(5, "lorem", true);
  }
}
