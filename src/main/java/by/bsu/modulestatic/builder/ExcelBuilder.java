package by.bsu.modulestatic.builder;


import by.bsu.modulestatic.entity.StatisticCalls;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import org.springframework.web.servlet.view.document.AbstractExcelView;


public class ExcelBuilder extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
        List<StatisticCalls> callsList = (List<StatisticCalls>) model.get("statisticCalls");
        List<String> valueList = (List<String>) model.get("statisticValues");


        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("Calls");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        // create header row
        HSSFRow header = sheet.createRow(0);

        header.createCell(0).setCellValue("Дата");
        header.getCell(0).setCellStyle(style);

        header.createCell(1).setCellValue("Регион");
        header.getCell(1).setCellStyle(style);

        header.createCell(2).setCellValue("Транспортное средство");
        header.getCell(2).setCellStyle(style);

        header.createCell(3).setCellValue("Причина");
        header.getCell(3).setCellStyle(style);

        header.createCell(4).setCellValue("Количество");
        header.getCell(4).setCellStyle(style);

        // create data rows
        int rowCount = 1;

        for (StatisticCalls aCalls : callsList) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(aCalls.getDate());
            aRow.createCell(1).setCellValue(aCalls.getRegionName());
            aRow.createCell(2).setCellValue(aCalls.getVechicleName());
            aRow.createCell(3).setCellValue(aCalls.getReasonName());
            aRow.createCell(4).setCellValue(aCalls.getCount());
        }

        header.createCell(5).setCellValue("Статистика");
        header.getCell(5).setCellStyle(style);

        header.createCell(6).setCellValue("Среднее значение");
        header.getCell(6).setCellStyle(style);

        header.createCell(7).setCellValue("Дисперсия");
        header.getCell(7).setCellStyle(style);

        header.createCell(8).setCellValue("Среднее квадратичное");
        header.getCell(8).setCellStyle(style);

        header.createCell(9).setCellValue("Размах вариации");
        header.getCell(9).setCellStyle(style);

        header.createCell(10).setCellValue("Коэффициент вариации");
        header.getCell(10).setCellStyle(style);

        header.createCell(11).setCellValue("Коэффициент осцилляции");
        header.getCell(11).setCellStyle(style);

        if(valueList!=null) {

            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(6).setCellValue(valueList.get(0));
            aRow.createCell(7).setCellValue(valueList.get(1));
            aRow.createCell(8).setCellValue(valueList.get(2));
            aRow.createCell(9).setCellValue(valueList.get(3));
            aRow.createCell(10).setCellValue(valueList.get(4));
            aRow.createCell(11).setCellValue(valueList.get(5));
        }

    }

}
