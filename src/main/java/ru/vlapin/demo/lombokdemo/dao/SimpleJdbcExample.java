package ru.vlapin.demo.lombokdemo.dao;

import java.sql.DriverManager;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;
import reactor.core.publisher.Flux;
import ru.vlapin.demo.lombokdemo.common.JdbcUtils;

/**
 * Утилитарный класс для демонстрации простого использования JDBC с потоковой передачей результатов.
 * <p>
 * Этот класс демонстрирует пример использования JDBC для подключения к базе данных,
 * выполнения запроса и передачи результатов с помощью парадигм реактивного программирования.
 * <p>
 * Используемые аннотации:
 * - {@code @UtilityClass}: помечает этот класс как класс утилиты, содержащий только статические методы.
 * - {@code @ExtensionMethod}: предоставляет методы расширения из внешних классов утилит,
 * таких как {@code JdbcUtils} и {@code Flux}.
 * <p>
 * Метод {@code main} демонстрирует:
 * - Установление соединения JDBC.
 * - Выполнение запроса для извлечения данных из таблицы студентов.
 * - Потоковая передача результатов с использованием {@code JdbcUtils#toStream} и их реактивная обработка.
 * <p>
 * Примечание: аннотация {@code @SneakyThrows} используется для обработки проверенных исключений,
 * а {@code @Cleanup} — для правильного управления ресурсами.
 */
@UtilityClass
@ExtensionMethod(suppressBaseMethods = false,
                 value = {
                     JdbcUtils.class,
                     Flux.class,
                 })
public class SimpleJdbcExample {

  /**
   * Выполняет запрос; передает результаты; обрабатывает исключения
   */
  @SneakyThrows
  void main() {
    @Cleanup val connection = DriverManager.getConnection("", "", "");
    @Cleanup val statement = connection.createStatement();
    @Cleanup val resultSet = statement.executeQuery("select * from student");

    resultSet.toStream(rs -> rs.getString("name"))
             .fromStream()
             .subscribe(IO::println);
  }
}
