package ru.vlapin.demo.lombokdemo.report;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueField;
import java.util.Optional;
import java.util.function.Supplier;
import lombok.val;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

public interface ExcelColumn  extends Titled {

  default void processHeaderStyle(XSSFCellStyle headerStyle, Supplier<? extends XSSFFont> fontSupplier) {
    headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

    val font = fontSupplier.get();
    font.setFontName("Arial");
    font.setFontHeightInPoints((short) 16);
    font.setBold(true);

    headerStyle.setFont(font);
  }

  default void processHeader(XSSFCellStyle style, XSSFCell cell) {

    processHeaderStyle(style, () -> cell.getSheet().getWorkbook().createFont());
    cell.setCellStyle(style);

    cell.setCellValue(title());
  }

  default void processStyle(XSSFCellStyle style, Supplier<? extends XSSFFont> fontSupplier) {
    style.setWrapText(true);
  }

  default String toValue(Issue issue) {
    return Optional.of(issue)
        .map(it -> it.getFieldByName(title()))
        .map(IssueField::getValue)
        .map(Object::toString)
        .orElse("");
  }

  default void processCell(XSSFCellStyle style,
                           XSSFCell cell,
                           Issue issue) {
    processStyle(style, () -> cell.getSheet().getWorkbook().createFont());
    cell.setCellStyle(style);

    val value = toValue(issue);
    cell.setCellValue(value);
  }
}
