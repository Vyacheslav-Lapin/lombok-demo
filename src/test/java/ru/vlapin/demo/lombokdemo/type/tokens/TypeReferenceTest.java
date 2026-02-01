package ru.vlapin.demo.lombokdemo.type.tokens;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
class TypeReferenceTest {

  @Test
  @DisplayName("Super Type-token works correctly")
  void superTypeTokenWorksCorrectlyTest() {
    // given

    // @formatter:off
    val l1 = new TypeReference<ArrayList<String>>(){}.newInstance();
    // @formatter:on

    l1.add("a");
    l1.add("b");
    l1.add("c");

    // when
    assertThat(l1)
        // then
        .isNotNull()
        .isExactlyInstanceOf(ArrayList.class)
        .hasSize(3)
        .containsExactly("a", "b", "c");
  }
}
