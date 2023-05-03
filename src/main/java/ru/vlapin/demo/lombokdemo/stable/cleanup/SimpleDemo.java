package ru.vlapin.demo.lombokdemo.stable.cleanup;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.sql.DriverManager;

import static ru.vlapin.demo.lombokdemo.stable.cleanup.Student.Fields.*;

public interface SimpleDemo {

  @SneakyThrows
  static void main(String... __) {

//    SPI
//    Class.forName("com.epam.h2db.Driver");

    @Cleanup val connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
    @Cleanup val statement = connection.createStatement();
    statement.executeUpdate("create table student (id identity, name varchar not null, group_id int)");
    statement.executeUpdate("insert into student (name, group_id) values ('Вася Пупкин', 123456), ('Федя Прокопов', 654321)");
    @Cleanup val resultSet = statement.executeQuery("select id, name, group_id as groupId from student");
    while (resultSet.next())
      System.out.println(
          Student.builder()
              .id(resultSet.getLong(id))
              .name(resultSet.getString(name))
              .groupId(resultSet.getInt(groupId)).build());
  }
}

@Builder
@FieldNameConstants
record Student(Long id, String name, int groupId) {
}
