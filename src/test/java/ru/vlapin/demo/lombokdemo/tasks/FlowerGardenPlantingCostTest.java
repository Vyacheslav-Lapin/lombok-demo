package ru.vlapin.demo.lombokdemo.tasks;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.tasks.FlowerGardenPlantingCost.*;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
class FlowerGardenPlantingCostTest {

  @Test
  @DisplayName("Minimal flower garden planting cost calculated correctly")
  void minimalFlowerGardenPlantingCostCalculatedCorrectlyTest() {
    // given
    int[][] costs = {{7, 4, 10}, {3, 6, 5}, {8, 1, 12}};

    // when
    assertThat(minCost(costs))
        // then
        .isEqualTo(8);

  }

  @Test
  @SneakyThrows
  @DisplayName(" works correctly")
  void worksCorrectlyTest() {
    // given
    int[][] costs = {{1, 2, 3}, {2, 4, 5}, {20, 1, 80}};

    // when
    assertThat(minCost(costs))
        // then
        .isEqualTo(5);
  }
}
