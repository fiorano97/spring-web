package dhi.ds.viewResolver;

import dhi.ds.domain.Solution;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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
        sheet.setDefaultColumnWidth(13);

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
        style.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);


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
        header.createCell(6).setCellValue("솔루션속성(Function)");
        header.getCell(6).setCellStyle(style);
        header.createCell(11).setCellValue("솔루션속성(Value)");
        header.getCell(11).setCellStyle(style);
        header.createCell(15).setCellValue("설비");
        header.getCell(15).setCellStyle(style);
        header.createCell(16).setCellValue("컴포넌트");
        header.getCell(16).setCellStyle(style);
        header.createCell(17).setCellValue("적용사례");
        header.getCell(17).setCellStyle(style);
        header.createCell(18).setCellValue("출처");
        header.getCell(18).setCellStyle(style);
        header.createCell(19).setCellValue("SW Functional View");
        header.getCell(19).setCellStyle(style);
        header.createCell(20).setCellValue("Asset Value View");
        header.getCell(20).setCellStyle(style);
        header.createCell(21).setCellValue("상세설명");
        header.getCell(21).setCellStyle(style);

        CellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font2 = workbook.createFont();
        font2.setColor(HSSFColor.BLACK.index);
        style2.setFont(font2);
        style2.setAlignment(HorizontalAlignment.CENTER);
        style2.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);


        CellStyle style3 = workbook.createCellStyle();
        style3.setAlignment(HorizontalAlignment.CENTER);
        style3.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);

        header = sheet.createRow(1);
        header.createCell(6).setCellValue("Monitoring");
        header.getCell(6).setCellStyle(style2);
        header.createCell(7).setCellValue("Prediction");
        header.getCell(7).setCellStyle(style2);
        header.createCell(8).setCellValue("Diagnosis");
        header.getCell(8).setCellStyle(style2);
        header.createCell(9).setCellValue("Optimization");
        header.getCell(9).setCellStyle(style2);
        header.createCell(10).setCellValue("Management");
        header.getCell(10).setCellStyle(style2);

        header.createCell(11).setCellValue("Efficiency");
        header.getCell(11).setCellStyle(style3);
        header.createCell(12).setCellValue("Flexibility");
        header.getCell(12).setCellStyle(style3);
        header.createCell(13).setCellValue("Availability");
        header.getCell(13).setCellStyle(style3);
        header.createCell(14).setCellValue("Emission");
        header.getCell(14).setCellStyle(style3);

        sheet.addMergedRegion(new CellRangeAddress(0,1,0,0));
        sheet.addMergedRegion(new CellRangeAddress(0,1,1,1));
        sheet.addMergedRegion(new CellRangeAddress(0,1,2,2));
        sheet.addMergedRegion(new CellRangeAddress(0,1,3,3));
        sheet.addMergedRegion(new CellRangeAddress(0,1,4,4));
        sheet.addMergedRegion(new CellRangeAddress(0,1,5,5));
        sheet.addMergedRegion(new CellRangeAddress(0,0,6,10));
        sheet.addMergedRegion(new CellRangeAddress(0,0,11,14));
        sheet.addMergedRegion(new CellRangeAddress(0,1,15,15));
        sheet.addMergedRegion(new CellRangeAddress(0,1,16,16));
        sheet.addMergedRegion(new CellRangeAddress(0,1,17,17));
        sheet.addMergedRegion(new CellRangeAddress(0,1,18,18));
        sheet.addMergedRegion(new CellRangeAddress(0,1,19,19));
        sheet.addMergedRegion(new CellRangeAddress(0,1,20,20));
        sheet.addMergedRegion(new CellRangeAddress(0,1,21,21));

        int rowCount = 2;

        CellStyle style4 = workbook.createCellStyle();
        style4.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);


        for(Solution solution : solutions){
            Row userRow =  sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(solution.getSolutionName());
            userRow.getCell(0).setCellStyle(style4);
            userRow.createCell(1).setCellValue(solution.getFunction());
            userRow.getCell(1).setCellStyle(style4);
            userRow.createCell(2).setCellValue(solution.getMaker());
            userRow.getCell(2).setCellStyle(style4);
            userRow.createCell(3).setCellValue(solution.getDevelopmentType());
            userRow.getCell(3).setCellStyle(style4);
            userRow.createCell(4).setCellValue(solution.getSolutionType());
            userRow.getCell(4).setCellStyle(style4);
            userRow.createCell(5).setCellValue(solution.getCustomerBenefit1());
            userRow.getCell(5).setCellStyle(style4);

            userRow.createCell(6).setCellValue(solution.getMonitoringYn());
            userRow.getCell(6).setCellStyle(style4);
            userRow.createCell(7).setCellValue(solution.getPredictionYn());
            userRow.getCell(7).setCellStyle(style4);
            userRow.createCell(8).setCellValue(solution.getDiagnosisYn());
            userRow.getCell(8).setCellStyle(style4);
            userRow.createCell(9).setCellValue(solution.getOptimizationYn());
            userRow.getCell(9).setCellStyle(style4);
            userRow.createCell(10).setCellValue(solution.getManagementYn());
            userRow.getCell(10).setCellStyle(style4);

            userRow.createCell(11).setCellValue(solution.getEfficiencyYn());
            userRow.getCell(11).setCellStyle(style4);
            userRow.createCell(12).setCellValue(solution.getFlexibilityYn());
            userRow.getCell(12).setCellStyle(style4);
            userRow.createCell(13).setCellValue(solution.getAvailabilityYn());
            userRow.getCell(13).setCellStyle(style4);
            userRow.createCell(14).setCellValue(solution.getEmissionYn());
            userRow.getCell(14).setCellStyle(style4);

            userRow.createCell(15).setCellValue(solution.getEquipment());
            userRow.getCell(15).setCellStyle(style4);
            userRow.createCell(16).setCellValue(solution.getComponent());
            userRow.getCell(16).setCellStyle(style4);
            userRow.createCell(17).setCellValue(solution.getReference());
            userRow.getCell(17).setCellStyle(style4);
            userRow.createCell(18).setCellValue(solution.getSource());
            userRow.getCell(18).setCellStyle(style4);
            userRow.createCell(19).setCellValue(solution.getSolutionGroup1());
            userRow.getCell(19).setCellStyle(style4);
            userRow.createCell(20).setCellValue(solution.getSolutionGroup2());
            userRow.getCell(20).setCellStyle(style4);
            userRow.createCell(21).setCellValue(solution.getDescription());
            userRow.getCell(21).setCellStyle(style4);
        }

    }

}
