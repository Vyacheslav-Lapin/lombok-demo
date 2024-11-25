package ru.vlapin.demo.lombokdemo.service;

import static ru.vlapin.demo.lombokdemo.model.Customer.Fields.*;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import ru.vlapin.demo.lombokdemo.common.JdbcUtils;
import ru.vlapin.demo.lombokdemo.dao.DBConnectionProvider;
import ru.vlapin.demo.lombokdemo.model.Customer;

//@Service
@RequiredArgsConstructor
public class CustomerService {

  DBConnectionProvider connectionProvider;

  @SneakyThrows
  public void createCustomer(@NotNull Customer customer) {
    @Cleanup val conn = connectionProvider.getConnection();
    @Cleanup val pstmt = conn.prepareStatement("INSERT INTO CUSTOMERS(ID, NAME) VALUES(?,?)");
    pstmt.setLong(1, customer.id());
    pstmt.setString(2, customer.name());
    pstmt.execute();
  }

  @SneakyThrows
  public List<Customer> getAllCustomers() {
    @Cleanup val conn = connectionProvider.getConnection();
    @Cleanup val pstmt = conn.prepareStatement("SELECT ID, NAME FROM CUSTOMERS");
    @Cleanup val resultSet = pstmt.executeQuery();

    return JdbcUtils.toStream(resultSet,
                              rs -> new Customer(rs.getLong(ID), rs.getString(NAME)))
                    .toList();
  }

  @SneakyThrows
  @PostConstruct
  void createCustomersTableIfNotExists() {
    @Cleanup val conn = connectionProvider.getConnection();
    @Cleanup val pstmt =
        conn.prepareStatement(
            """
          CREATE TABLE IF NOT EXISTS CUSTOMERS (
              ID BIGINT NOT NULL,
              NAME VARCHAR NOT NULL,
              PRIMARY KEY (ID)
          )""");

    pstmt.execute();
  }
}
