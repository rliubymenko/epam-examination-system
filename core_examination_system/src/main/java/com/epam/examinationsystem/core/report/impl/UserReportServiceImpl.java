package com.epam.examinationsystem.core.report.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.report.UserReportService;
import com.epam.examinationsystem.core.service.UserService;
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
public class UserReportServiceImpl implements UserReportService {

    private static final Logger LOG = LoggerFactory.getLogger(UserReportServiceImpl.class);

    @PleaseInject
    private UserService userService;

    @Override
    public void createUserReport(OutputStream outputStream) throws ServiceException {
        LOG.debug("Creating user pdf report");
        Document document = new Document(PageSize.A4.rotate());
        List<UserDto> users = userService.findAll();
        PdfWriter.getInstance(document, outputStream);

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16);
        fontTitle.setColor(CMYKColor.DARK_GRAY);
        Paragraph paragraph = new Paragraph("List of the Users", fontTitle);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        PdfPTable table = new PdfPTable(7);

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
        cell.setPhrase(new Phrase("username", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("email", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("first_name", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("last_name", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("role", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("is_activated", headerFont));
        table.addCell(cell);

        for (UserDto user : users) {
            table.addCell(user.getUuid());
            table.addCell(user.getUsername());
            table.addCell(user.getEmail());
            table.addCell(user.getFirstName());
            table.addCell(user.getLastName());
            table.addCell(user.getRole());
            table.addCell(user.getIsActivated().toString());
        }
        document.add(table);
        document.close();
    }
}
