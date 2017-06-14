package dhi.ds.viewResolver;

import dhi.ds.domain.Solution;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import static org.apache.poi.hssf.record.ExtendedFormatRecord.CENTER;

public class ExcelView extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        String dateFormat = "yyyyMMdd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        response.setHeader("Content-Disposition", "attachment; filename=\"DigitalSolution_" + simpleDateFormat.format(calendar.getTime()) + ".xls\"");

        @SuppressWarnings("unchecked")
        List<Solution> solutions = (List<Solution>) model.get("solutions");

        // create excel xls sheet
        Sheet sheet = workbook.createSheet("Digital Solutions");
        sheet.setDefaultColumnWidth(15);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);


        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("솔루션명");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("주요기능");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("제작사");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("유형");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("형태");
        header.getCell(4).setCellStyle(style);
        header.createCell(5).setCellValue("고객Value");
        header.getCell(5).setCellStyle(style);
        header.createCell(6).setCellValue("설비");
        header.getCell(6).setCellStyle(style);
        header.createCell(7).setCellValue("컴포넌트");
        header.getCell(7).setCellStyle(style);
        header.createCell(8).setCellValue("적용사례");
        header.getCell(8).setCellStyle(style);
        header.createCell(9).setCellValue("출처");
        header.getCell(9).setCellStyle(style);
        header.createCell(10).setCellValue("SW Functional View");
        header.getCell(10).setCellStyle(style);
        header.createCell(11).setCellValue("Asset Value View");
        header.getCell(11).setCellStyle(style);
        header.createCell(12).setCellValue("상세설명");
        header.getCell(12).setCellStyle(style);

        int rowCount = 1;

        for(Solution solution : solutions){
            Row userRow =  sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(solution.getSolutionName());
            userRow.createCell(1).setCellValue(solution.getFunction());
            userRow.createCell(2).setCellValue(solution.getMaker());
            userRow.createCell(3).setCellValue(solution.getDevelopmentType());
            userRow.createCell(4).setCellValue(solution.getSolutionType());
            userRow.createCell(5).setCellValue(solution.getCustomerBenefit1());
            userRow.createCell(6).setCellValue(solution.getEquipment());
            userRow.createCell(7).setCellValue(solution.getComponent());
            userRow.createCell(8).setCellValue(solution.getReference());
            userRow.createCell(9).setCellValue(solution.getSource());
            userRow.createCell(10).setCellValue(solution.getSolutionGroup1());
            userRow.createCell(11).setCellValue(solution.getSolutionGroup2());
            userRow.createCell(12).setCellValue(solution.getDescription());
        }

    }

}
