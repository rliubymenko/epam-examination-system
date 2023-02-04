package com.epam.examinationsystem.core.report.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.SubjectDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.report.SubjectReportService;
import com.epam.examinationsystem.core.service.SubjectService;
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
public class SubjectReportServiceImpl implements SubjectReportService {

    private static final Logger LOG = LoggerFactory.getLogger(SubjectReportServiceImpl.class);

    @PleaseInject
    private SubjectService subjectService;

    @Override
    public void createSubjectReport(OutputStream outputStream) throws ServiceException {
        LOG.debug("Creating subject pdf report");
        Document document = new Document(PageSize.A4.rotate());
        List<SubjectDto> subjects = subjectService.findAll();
        PdfWriter.getInstance(document, outputStream);

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16);
        fontTitle.setColor(CMYKColor.DARK_GRAY);
        Paragraph paragraph = new Paragraph("List of the Subjects", fontTitle);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        PdfPTable table = new PdfPTable(3);

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

        for (SubjectDto subject : subjects) {
            table.addCell(subject.getUuid());
            table.addCell(subject.getName());
            table.addCell(subject.getDescription());
        }
        document.add(table);
        document.close();
    }
}
