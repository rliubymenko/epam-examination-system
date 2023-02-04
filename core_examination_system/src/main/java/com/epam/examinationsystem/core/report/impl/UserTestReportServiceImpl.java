package com.epam.examinationsystem.core.report.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.UserTestDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.report.UserTestReportService;
import com.epam.examinationsystem.core.service.UserTestService;
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
public class UserTestReportServiceImpl implements UserTestReportService {

    private static final Logger LOG = LoggerFactory.getLogger(UserTestReportServiceImpl.class);

    @PleaseInject
    private UserTestService userTestService;

    @Override
    public void createUserTestReport(OutputStream outputStream) throws ServiceException {
        LOG.debug("Creating user test pdf report");
        Document document = new Document(PageSize.A4.rotate());
        List<UserTestDto> userTests = userTestService.findAll();
        PdfWriter.getInstance(document, outputStream);

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16);
        fontTitle.setColor(CMYKColor.DARK_GRAY);
        Paragraph paragraph = new Paragraph("List of the User tests", fontTitle);
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
        cell.setPhrase(new Phrase("is_selected", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("is_completed", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("mark_value", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("attempt_number", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("start_time", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("end_time", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("user_uuid", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("user_username", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("test_uuid", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("test_name", headerFont));
        table.addCell(cell);

        for (UserTestDto userTest : userTests) {
            table.addCell(userTest.getUuid());
            table.addCell(userTest.getIsSelected());
            table.addCell(userTest.getIsCompleted());
            table.addCell(userTest.getMarkValue());
            table.addCell(userTest.getAttemptNumber());
            table.addCell(userTest.getStartTime());
            table.addCell(userTest.getEndTime());
            table.addCell(userTest.getUser().getUuid());
            table.addCell(userTest.getUser().getUsername());
            table.addCell(userTest.getTest().getUuid());
            table.addCell(userTest.getTest().getName());
        }
        document.add(table);
        document.close();
    }
}
