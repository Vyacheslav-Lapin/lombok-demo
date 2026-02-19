package ru.vlapin.demo.lombokdemo.common;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.vavr.CheckedFunction1;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import lombok.experimental.ExtensionMethod;
import lombok.val;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link JdbcUtils#toIterator(ResultSet, CheckedFunction1)} method. This method converts a {@link ResultSet} into an
 * {@link Iterator} using a user-defined mapping function.
 */
@ExtensionMethod(value = JdbcUtils.class, suppressBaseMethods = false)
class JdbcUtilsTest {

  @Test
  void toIterator_shouldReturnIteratorForResultSet() throws SQLException {
    // Arrange
    val mockResultSet = mock(ResultSet.class);
    when(mockResultSet.isLast()).thenReturn(false, false, true); // 2 rows and then end
    when(mockResultSet.next()).thenReturn(true, true, false);
    when(mockResultSet.getString("column")).thenReturn("Row 1", "Row 2");

    // Act
    val iterator = mockResultSet.toIterator(rs ->
        rs.getString("column"));

    // Assert
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.next()).isEqualTo("Row 1");
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.next()).isEqualTo("Row 2");
    assertThat(iterator.hasNext()).isFalse();

    verify(mockResultSet, times(2)).getString("column");
    verify(mockResultSet, times(2)).next();
    verify(mockResultSet, times(3)).isLast();
  }

  @Test
  void toIterator_shouldThrowExceptionWhenNoMoreRows() throws SQLException {
    // Arrange
    val mockResultSet = mock(ResultSet.class);
    when(mockResultSet.isLast()).thenReturn(true);
    when(mockResultSet.next()).thenReturn(false); // No rows available

    // Act
    val iterator = mockResultSet.toIterator(rs ->
        rs.getString("column"));

    // Assert
    assertThrows(RuntimeException.class, iterator::next);

    verify(mockResultSet, times(1)).next();
    verify(mockResultSet, never()).isLast();
  }

  @Test
  void toIterator_shouldHandleEmptyResultSet() throws SQLException {
    // Arrange
    val mockResultSet = mock(ResultSet.class);
    when(mockResultSet.isLast()).thenReturn(true); // Immediately last
    when(mockResultSet.next()).thenReturn(false);  // No rows to iterate

    // Act
    val iterator = mockResultSet.toIterator(rs ->
        rs.getString("column"));

    // Assert
    assertThat(iterator.hasNext()).isFalse();

    verify(mockResultSet, never()).getString(anyString());
    verify(mockResultSet, times(1)).isLast();
    verify(mockResultSet, never()).next();
  }

  @Test
  void toIterator_shouldPropagateMapperExceptions() throws SQLException {
    // Arrange
    val mockResultSet = mock(ResultSet.class);
    when(mockResultSet.isLast()).thenReturn(false, true);
    when(mockResultSet.next()).thenReturn(true, false);
    when(mockResultSet.getString("column")).thenThrow(new SQLException("Mapping failed"));

    // Act
    val iterator = mockResultSet.toIterator(rs ->
        rs.getString("column"));

    // Assert
    assertThrows(SQLException.class, iterator::next);

    verify(mockResultSet, times(1)).next();
    verify(mockResultSet, times(1)).getString("column");
  }
}
