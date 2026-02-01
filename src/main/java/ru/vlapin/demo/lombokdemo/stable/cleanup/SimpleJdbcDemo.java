package ru.vlapin.demo.lombokdemo.stable.cleanup;

import static ru.vlapin.demo.lombokdemo.stable.cleanup.Student.Fields.*;

import java.sql.DriverManager;
import lombok.Builder;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.FieldNameConstants;
import lombok.val;

@SuppressWarnings({"java:S6437", "java:S125", "java:S106"})
public interface SimpleJdbcDemo {

  @SneakyThrows
  static void main(String... __) {

//    SPI — no need to do this:
//    Class.forName("org.postgresql.Driver");

    @Cleanup val connection = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres",
        "postgres",
        "postgres");

    @Cleanup val statement = connection.createStatement();
    statement.executeUpdate("drop table if exists student");
    statement.executeUpdate("create table student (id serial primary key, name varchar not null, group_id int)");

    //noinspection SqlResolve
    statement.executeUpdate("""
        insert into student (name, group_id)
        values ('Вася Пупкин', 123456),
               ('Федя Прокопов', 654321)""");

    @Cleanup val resultSet = statement.executeQuery("""
        -- noinspection SqlResolve
        select id, name, group_id as groupId from student""");

    while (resultSet.next())
      System.out.println(
          Student.builder()
              .id(resultSet.getLong(ID))
              .name(resultSet.getString(NAME))
              .groupId(resultSet.getInt(GROUP_ID)).build());
  }
}

@Builder
@FieldNameConstants
record Student(Long id, String name, int groupId) {
}
