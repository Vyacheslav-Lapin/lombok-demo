package ru.vlapin.demo.lombokdemo.tasks;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import org.checkerframework.common.value.qual.ArrayLen;

/**
 * Задачка с ссобеседования в одну западную компанию.<br/><br/>
 * Вам предстоит спланировать посадку цветов в ряду садов, чтобы получилась яркая картина. Каждый сад должен быть
 * засажен одним из трех видов цветов: Розы, Тюльпаны или Маргаритки. Стоимость посадки каждого вида цветов в каждом
 * саду различна. Ваша задача - засадить все сады, не допустив, чтобы в двух соседних садах были цветы одного типа.<br/><br/>
 *
 * Стоимость посадки представлена в виде:
 * <pre>{@code
 *   [[7, 4, 10], [3, 6, 5], [8, 1, 12]]
 * }</pre><br/>
 *
 * {@code n×3} матрицы затрат {@code costs}.<br/><br/>
 * В этом примере:
 * <ul>
 *   <li>{@code costs[0][0]} - стоимость посадки роз в саду №{@code 0};</li>
 *   <li>{@code costs[1][2]} - стоимость посадки маргариток в саду №{@code 1},</li>
 *   <li>и так далее...</li>
 * </ul>
 *
 * Задача:<br/>
 * Вернуть минимальную стоимость посадки всех садов так, чтобы ни в одном из двух соседних садов не было цветов одного
 * и того же типа.<br/><br/>
 *
 * Пример:<br/>
 * Входные данные: {@code costs = [[7, 4, 10], [3, 6, 5], [8, 1, 12]]}<br/>
 * Вывод: {@code 4 + 3 + 1 = 8}
 */
@UtilityClass
@ExtensionMethod(Arrays.class)
public class FlowerGardenPlantingCost {
  public int minCost(int[] @ArrayLen(3) [] costs) {
    //noinspection OptionalGetWithoutIsPresent
    return costs.stream()
                 .reduce((previousCostSum, cost) -> new int[] {
                     cost[0] + Math.min(previousCostSum[1], previousCostSum[2]),
                     cost[1] + Math.min(previousCostSum[0], previousCostSum[2]),
                     cost[2] + Math.min(previousCostSum[0], previousCostSum[1])})
                 .map(Arrays::stream)
                 .map(IntStream::min)
                 .map(OptionalInt::getAsInt).get();
  }
}
