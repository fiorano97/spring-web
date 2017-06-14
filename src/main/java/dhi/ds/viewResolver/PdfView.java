package dhi.ds.viewResolver;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dhi.ds.domain.Solution;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class PdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String dateFormat = "yyyyMMdd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        response.setHeader("Content-Disposition", "attachment; filename=\"DigitalSolution_" + simpleDateFormat.format(calendar.getTime()) + ".pdf\"");

        List<Solution> solutions = (List<Solution>) model.get("solutions");
        document.add(new Paragraph("Digital Solutions " + LocalDate.now()));
        float[] columnWidths = {2, 4, 2, 1, 1, 2, 1, 1, 2, 2};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100.0f);
        table.setSpacingBefore(10);

        Resource resource = new ClassPathResource("static/font/malgun.ttf");
        File file = resource.getFile();

        BaseFont bfKorean = BaseFont.createFont(file.getCanonicalPath(), BaseFont.IDENTITY_H,
                BaseFont.EMBEDDED);
        Font font = new Font(bfKorean);
        font.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.GRAY);
        cell.setPadding(5);

        // write table header
        cell.setPhrase(new Phrase("솔루션명", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("주요기능", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("제작사", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("유형", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("형태", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("고객Value", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("설비", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("컴포넌트", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("적용사례", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("출처", font));
        table.addCell(cell);

        cell.setBackgroundColor(BaseColor.WHITE);
        font.setColor(BaseColor.BLACK);
        for(Solution solution : solutions){
            cell.setPhrase(new Phrase(solution.getSolutionName(), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(solution.getFunction(), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(solution.getMaker(), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(solution.getDevelopmentType(), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(solution.getSolutionType(), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(solution.getCustomerBenefit1(), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(solution.getEquipment(), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(solution.getComponent(), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(solution.getReference(), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(solution.getSource(), font));
            table.addCell(cell);
        }

        document.add(table);
    }
}
