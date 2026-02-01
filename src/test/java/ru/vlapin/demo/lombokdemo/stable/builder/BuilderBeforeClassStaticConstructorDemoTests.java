package ru.vlapin.demo.lombokdemo.stable.builder;

import static org.assertj.core.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
class BuilderBeforeClassStaticConstructorDemoTests {

  @Test
  @DisplayName("@Builder castomization works correctly")
  void builderCustomizationWorksCorrectlyTest() {
    val creator = BuilderBeforeClassCustomizationDemo.creator();
    assertThat(creator.withX(5)
                   .withB(true)
                   .withS("lorem").create())
        .extracting("x", "s", "b")
        .contains(5, "lorem", true);
  }
}
