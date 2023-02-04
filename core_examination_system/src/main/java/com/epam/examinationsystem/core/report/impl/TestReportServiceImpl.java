package com.epam.examinationsystem.core.report.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.TestDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.report.TestReportService;
import com.epam.examinationsystem.core.service.TestService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.util.List;

@PleaseService
public class TestReportServiceImpl implements TestReportService {

    private static final Logger LOG = LoggerFactory.getLogger(TestReportServiceImpl.class);

    @PleaseInject
    private TestService testService;

    @Override
    public void createTestReport(OutputStream outputStream) throws ServiceException {
        LOG.debug("Creating answer pdf report");
        Document document = new Document(PageSize.A4.rotate());
        List<TestDto> tests = testService.findAll();
        PdfWriter.getInstance(document, outputStream);

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16);
        fontTitle.setColor(CMYKColor.DARK_GRAY);
        Paragraph paragraph = new Paragraph("List of the Tests", fontTitle);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        PdfPTable table = new PdfPTable(11);

        table.setWidthPercentage(100);
        table.setSpacingBefore(5);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(CMYKColor.DARK_GRAY);
        cell.setPadding(5);
        Font headerFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        headerFont.setColor(CMYKColor.WHITE);
        headerFont.setSize(14);

        cell.setPhrase(new Phrase("uuid", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("name", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("description", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("complexity", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("duration", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("total_attempt_number", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("expiration_date", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("max_attempt_number", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("created", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("subject_uuid", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("subject_name", headerFont));
        table.addCell(cell);

        for (TestDto test : tests) {
            table.addCell(test.getUuid());
            table.addCell(test.getName());
            table.addCell(test.getDescription());
            table.addCell(test.getComplexity());
            table.addCell(test.getDuration());
            table.addCell(test.getTotalAttemptNumber());
            table.addCell(test.getExpirationDate());
            table.addCell(test.getMaxAttemptNumber());
            table.addCell(test.getCreationDate());
            if (test.getSubject() != null) {
                table.addCell(test.getSubject().getUuid());
                table.addCell(test.getSubject().getName());
            } else {
                table.addCell("null");
                table.addCell("null");
            }
        }
        document.add(table);
        document.close();
    }
}
