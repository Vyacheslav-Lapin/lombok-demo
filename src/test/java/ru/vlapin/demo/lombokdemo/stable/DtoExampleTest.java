package ru.vlapin.demo.lombokdemo.stable;

import static org.assertj.core.api.Assertions.*;

import java.util.Date;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * Test class for {@code DtoExample}.
 * <p>
 * This test ensures that the fluent-style setters and getters of the
 * {@code DtoExample} class function as expected and that the object
 * retains the correct state after being initialized and accessed.
 * <p>
 * This class uses JUnit 5 annotations for unit testing.
 * <p>
 * Annotations:
 * <ul>
 *   <li>{@code @Test}: Marks the test method to be executed by the test runner</li>
 *   <li>{@code @DisplayName}: Provides a human-readable name for the test</li>
 </ul>
 * Test Method:
 * <ul>
 *   <li>{@code worksCorrectlyTest()}: Verifies that the {@code DtoExample} class:
 *     <ul>
 *       <li>Accepts proper initialization of its fields using fluent setters</li>
 *       <li>Accurately returns the expected values when fields are accessed using getters</li>
 *       <li>Ensures the DTO is not null after creation</li>
 *       <li>Confirms that the extracted field values match the expected inputs</li>
 *     </ul>
 *   </li>
 * </ul>
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class DtoExampleTest {

  @Test
  @DisplayName("DtoExample works correctly")
  void dtoExampleWorksCorrectlyTest() {
    // given
    val date = new Date(45_678);
    val value = 5_634;
    val dtoExample = new DtoExample()
        .x(date)
        .y(value);

    // when
    assertThat(dtoExample).isNotNull()
        // then
        .extracting(DtoExample::x, DtoExample::y)
        .contains(date, value);
  }
}
