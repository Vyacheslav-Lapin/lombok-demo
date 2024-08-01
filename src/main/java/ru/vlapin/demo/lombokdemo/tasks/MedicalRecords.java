package ru.vlapin.demo.lombokdemo.tasks;

import static java.util.Spliterator.*;
import static lombok.AccessLevel.*;
import static ru.vlapin.demo.lombokdemo.tasks.MedicalRecords.*;

import io.vavr.CheckedFunction2;
import io.vavr.Tuple;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.Cleanup;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.NonFinal;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;

//import java.io.*;
//import java.math.*;
//import java.security.*;
//import java.text.*;
//import java.util.*;
//import java.util.concurrent.*;
//import java.util.function.*;
//import java.util.regex.*;
//import java.util.stream.*;
//import static java.util.stream.Collectors.joining;
//import static java.util.stream.Collectors.toList;
//import java.net.*;
//import org.json.simple.*;
//import org.json.simple.parser.*;

/**
 * Задача с собеседования, hackerrank.<br/><br/>
 * <p>
 * Используйте метод HTTP GET для получения информации из базы данных медицинских карт пациентов. Запрос
 * <a href="https://jsonmock.hackerrank.com/api/medical_re">https://jsonmock.hackerrank.com/api/medical_re</a> позволяет найти все записи.
 * Результат запроса постраничен, и к нему можно получить доступ, добавив к строке запроса ?page=num, где num - номер страницы (начиная с
 * 1).<br/><br/>
 * <p>
 * Ответ на запрос от API представляет собой объект JSON со следующими пятью полями:
 * <ul>
 *  <li>{@code page}: текущая страница</li>
 *  <li>{@code per_page}: максимальное количество результатов на странице</li>
 *  <li>{@code total}: общее количество записей в результатах поиска</li>
 *  <li>{@code total_pages}: общее количество страниц, которые необходимо запросить, чтобы получить все результаты</li>
 *  <li>{@code data}: массив JSON-объектов, содержащих медицинские записи.</li>
 * </ul>
 * Поле {@code data} в ответе содержит список медицинских записей со следующей схемой:
 * <ul>
 *  <li>{@code id}: уникальный идентификатор записи</li>
 *  <li>{@code timestamp}: временная метка создания записи (в UTC в миллисекундах)</li>
 *  <li>{@code userld}: идентификатор пользователя, для которого была записана транзакция</li>
 *  <li>{@code userName}: имя пациента, для которого была зарегистрирована транзакция</li>
 *  <li>{@code userDob}: дата рождения пользователя в формате DD-MM-YYYY</li>
 *  <li>{@code vitals}: объект, показатели жизнедеятельности пользователя:
 *    <ul>
 *     <li>{@code vitals.bloodPressureDiastole}: показания диастолического давления пользователя, мм рт. ст.</li>
 *     <li>{@code vitals.bloodPressureSystole}: показания систолического давления пользователя, мм рт. ст.</li>
 *     <li>{@code vitals.pulse}: частота пульса пользователя, ударов в минуту</li>
 *     <li>{@code vitals.breathingRate}: частота дыхания пользователя, вдохов в минуту</li>
 *     <li>{@code vitals.bodyTemperature}: температура тела пользователя, градусы по Фаренгейту</li>
 *    </ul></li>
 *  <li>{@code diagnosis}: объект, диагноз пользователя
 *    <ul>
 *     <li>{@code diagnosis.id}: идентификатор диагностируемого состояния</li>
 *     <li>{@code diagnosis.name}: название диагностируемого состояния</li>
 *     <li>{@code diagnosis.severity}: степень тяжести диагностированного состояния</li>
 *    </ul></li>
 *  <li>{@code doctor}: объект, врач, диагностировавший состояние
 *    <ul>
 *     <li>{@code doctor.id}: идентификатор врача, диагностировавшего состояние</li>
 *     <li>{@code doctor.name}: имя врача, диагностировавшего заболевание</li>
 *    </ul></li>
 *  <li>{@code meta}: объект, метаинформация о пользователе
 *    <ul>
 *     <li>{@code meta.height}: Текущий рост пользователя, в сантиметрах</li>
 *     <li>{@code meta.weight}: Текущий вес пользователя, фунты</li>
 *    </ul></li>
 * </ul>
 * <p>
 * Учитывая имя врача и идентификатор диагноза, отфильтруйте записи по всем страницам. Верните массив с двумя целыми числами - целыми частями минимальной и максимальной температуры тела.
 */
@Slf4j
@UtilityClass
@ExtensionMethod(value = {
    Spliterators.class,
    StreamSupport.class,
}, suppressBaseMethods = false)
public class MedicalRecords {

  public final BiFunction<JSONObject, String, Integer> GET_INT = CheckedFunction2.of(JSONObject::getInt).unchecked();
  public final BiFunction<JSONObject, String, Long> GET_LONG = CheckedFunction2.of(JSONObject::getLong).unchecked();
  public final BiFunction<JSONObject, String, String> GET_STRING = CheckedFunction2.of(JSONObject::getString).unchecked();
  public final BiFunction<JSONObject, String, JSONObject> GET_OBJECT = CheckedFunction2.of(JSONObject::getJSONObject).unchecked();

  /**
   * Завершите функцию 'bodyTemperature', приведенную ниже.
   *
   * @param doctorName  имя врача
   * @param diagnosisId идентификатор диагноза
   * @return int[2]: минимальная и максимальная температура тела
   * @apiNote URL: https://jsonmock.hackerrank.com/api/medical_records?page={page_no}
   * @implNote Пожалуйста, просмотрите заголовок в кодовой заглушке, чтобы увидеть доступные библиотеки для API-запросов на выбранном языке.
   * Необходимые библиотеки могут быть импортированы для решения вопроса. Ознакомьтесь с полным списком поддерживаемых библиотек на сайте
   * <a href="https://www.hackerrank.com/environment">https://www.hackerrank.com/environment</a>.
   */
  public List<Integer> bodyTemperature(String doctorName, int diagnosisId) {
    return new LazyMedicalRecordsClientIterator()
        .spliteratorUnknownSize(ORDERED | IMMUTABLE | SORTED)
        .stream(false)
        .map(MedicalRecord::new)
        .filter(medicalRecord -> medicalRecord.doctor().name().equals(doctorName))
        .filter(medicalRecord -> medicalRecord.diagnosis().id() == diagnosisId)
        .map(MedicalRecord::vitals)
        .map(Vitals::bodyTemperature)

        .reduce(
            Tuple.of(Integer.MAX_VALUE, Integer.MIN_VALUE),

            (minAndMax, bodyTemperature) ->
                minAndMax.apply((min, max) -> Tuple.of(
                    min > bodyTemperature ? bodyTemperature : min,
                    max < bodyTemperature ? bodyTemperature : max)),

            (min1AndMax1, minAndMax2) ->
                min1AndMax1.apply((min1, max1) ->
                    minAndMax2.apply((min2, max2) ->
                        Tuple.of(
                            min1 > min2 ? min2 : min1,
                            max1 < max2 ? max2 : max1))))

        .apply(List::of);
  }
}

@Slf4j
class LazyMedicalRecordsClientIterator implements Iterator<JSONObject> {

  private static final Function<Integer, String> TO_URL =
      "https://jsonmock.hackerrank.com/api/medical_records?page=%d"::formatted;
  int totalPages;
  @NonFinal int cursor = -1; // элемент, который следующим будет выдан методом next
  @NonFinal int page; // номер текущей страницы
  @NonFinal JSONArray data;

  @SneakyThrows
  public LazyMedicalRecordsClientIterator() {
    val responseBody = getResponseBody(TO_URL.apply(1));
    val jsonObject = new JSONObject(responseBody);

    page = jsonObject.getInt("page");
    totalPages = jsonObject.getInt("total_pages");
    data = jsonObject.getJSONArray("data");
  }

  @Override
  public boolean hasNext() {
    return cursor < data.length() - 1 || page < totalPages;
  }

  @Override
  @SneakyThrows
  public JSONObject next() {
    if (cursor < data.length() - 1) {
      return data.getJSONObject(++cursor);
    } else if (page < totalPages) {

      data = new JSONObject(
          getResponseBody(
              TO_URL.apply(++page)
          )).getJSONArray("data");

      cursor = 0;

      return data.getJSONObject(cursor);
    } else {
      throw new NoSuchElementException();
    }
  }

  @SneakyThrows
  public String getResponseBody(String url) {
    // return IOUtils.toString(URI.create(url), UTF_8);
    if (!(URI.create(url).toURL().openConnection() instanceof HttpURLConnection conn)) {
      throw new IOException("HTTP protocol isn't supported");
    }

    conn.connect();

    // Parse response
    @Cleanup val reader = new BufferedReader(
        new InputStreamReader(
            conn.getInputStream()));

    return reader.lines().collect(Collectors.joining());
  }
}

@RequiredArgsConstructor
@ExtensionMethod(value = {
    LocalDate.class,
}, suppressBaseMethods = false)
@FieldNameConstants(level = PRIVATE)
class MedicalRecord {

  public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

  @Getter(NONE)
  @FieldNameConstants.Exclude
  JSONObject jsonObject;

  /**
   * Уникальный идентификатор записи
   */
  @Getter(lazy = true)
  int id = GET_INT.apply(jsonObject, Fields.ID);

  /**
   * Временная метка создания записи (в UTC в миллисекундах)
   */
  @Getter(lazy = true)
  long timestamp = GET_LONG.apply(jsonObject, Fields.TIMESTAMP);

  /**
   * Идентификатор пользователя, для которого была записана транзакция
   */
  @Getter(lazy = true)
  int userId = GET_INT.apply(jsonObject, Fields.USER_ID);

  /**
   * Имя пациента, для которого была зарегистрирована транзакция
   */
  @Getter(lazy = true)
  String userName = GET_STRING.apply(jsonObject, Fields.USER_NAME);

  /**
   * Дата рождения пользователя в формате DD-MM-YYYY
   */
  @Getter(lazy = true)
  LocalDate userDob = GET_STRING.apply(jsonObject, Fields.USER_DOB)
                                .parse(DATE_FORMATTER);

  /**
   * Показатели жизнедеятельности пользователя
   */
  @Getter(lazy = true)
  Vitals vitals = new Vitals(GET_OBJECT.apply(jsonObject, Fields.VITALS));

  /**
   * Диагноз пользователя
   */
  @Getter(lazy = true)
  Diagnosis diagnosis = new Diagnosis(GET_OBJECT.apply(jsonObject, Fields.DIAGNOSIS));

  /**
   * Врач, диагностировавший состояние
   */
  @Getter(lazy = true)
  Doctor doctor = new Doctor(GET_OBJECT.apply(jsonObject, Fields.DOCTOR));

  /**
   * Метаинформация о пользователе
   */
  @Getter(lazy = true)
  Meta meta = new Meta(GET_OBJECT.apply(jsonObject, Fields.META));
}

@RequiredArgsConstructor
@FieldNameConstants(level = PRIVATE)
class Vitals {

  @Getter(NONE)
  @FieldNameConstants.Exclude
  JSONObject jsonObject;

  /**
   * Показания диастолического давления пользователя, мм рт. ст.
   */
  @Getter(lazy = true)
  int bloodPressureDiastole = GET_INT.apply(jsonObject, Fields.BLOOD_PRESSURE_DIASTOLE);

  /**
   * Показания систолического давления пользователя, мм рт. ст.
   */
  @Getter(lazy = true)
  int bloodPressureSystole = GET_INT.apply(jsonObject, Fields.BLOOD_PRESSURE_SYSTOLE);

  /**
   * Частота пульса пользователя, ударов в минуту
   */
  @Getter(lazy = true)
  int pulse = GET_INT.apply(jsonObject, Fields.PULSE);

  /**
   * Частота дыхания пользователя, вдохов в минуту
   */
  @Getter(lazy = true)
  int breathingRate = GET_INT.apply(jsonObject, Fields.BREATHING_RATE);

  /**
   * Температура тела пользователя, градусы по Фаренгейту
   */
  @Getter(lazy = true)
  int bodyTemperature = GET_INT.apply(jsonObject, Fields.BODY_TEMPERATURE);
}

@RequiredArgsConstructor
@FieldNameConstants(level = PRIVATE)
class Diagnosis {

  @Getter(NONE)
  @FieldNameConstants.Exclude
  JSONObject jsonObject;

  /**
   * Идентификатор диагностируемого состояния
   */
  @Getter(lazy = true)
  int id = GET_INT.apply(jsonObject, Fields.ID);

  /**
   * Название диагностируемого состояния
   */
  @Getter(lazy = true)
  String name = GET_STRING.apply(jsonObject, Fields.NAME);

  /**
   * Степень тяжести диагностированного состояния
   */
  @Getter(lazy = true)
  int severity = GET_INT.apply(jsonObject, Fields.SEVERITY);
}

@RequiredArgsConstructor
@FieldNameConstants(level = PRIVATE)
class Doctor {

  @Getter(NONE)
  @FieldNameConstants.Exclude
  JSONObject jsonObject;

  /**
   * Идентификатор врача, диагностировавшего состояние
   */
  @Getter(lazy = true)
  int id = GET_INT.apply(jsonObject, Fields.ID);

  /**
   * Имя врача, диагностировавшего заболевание
   */
  @Getter(lazy = true)
  String name = GET_STRING.apply(jsonObject, Fields.NAME);
}

@RequiredArgsConstructor
@FieldNameConstants(level = PRIVATE)
class Meta {

  @Getter(NONE)
  @FieldNameConstants.Exclude
  JSONObject jsonObject;

  /**
   * Текущий рост пользователя, в сантиметрах
   */
  @Getter(lazy = true)
  int height = GET_INT.apply(jsonObject, Fields.HEIGHT);

  /**
   * Текущий вес пользователя, фунты
   */
  @Getter(lazy = true)
  int weight = GET_INT.apply(jsonObject, Fields.WEIGHT);
}
