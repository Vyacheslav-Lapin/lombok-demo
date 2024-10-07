package ru.vlapin.demo.lombokdemo.report;

import static lombok.AccessLevel.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;
import lombok.experimental.NonFinal;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@RequiredArgsConstructor
@Accessors(fluent = true)
public class SingleSheetExcelReport<C extends ExcelColumn> implements AutoCloseable {

  C[] columns;

  @Getter(value = PRIVATE, lazy = true)
  @Delegate(types = AutoCloseable.class)
  XSSFWorkbook workbook = new XSSFWorkbook();

  @SuppressWarnings("resource")
  @Getter(value = PRIVATE, lazy = true)
  XSSFSheet sheet = workbook().createSheet();

  @NonFinal int rowNum;
  @NonFinal int cellNum;

  public XSSFRow row() {
    cellNum = 0;
    return sheet().createRow(rowNum++);
  }

  XSSFCell cell(CellType type) {
    return row().createCell(cellNum++, type);
  }

  XSSFCell cell() {
    return row().createCell(cellNum++);
  }
}
