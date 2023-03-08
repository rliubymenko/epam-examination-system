package com.epam.examinationsystem.core.report.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.QuestionDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.report.QuestionReportService;
import com.epam.examinationsystem.core.service.QuestionService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.util.List;

/**
 * The class for generating question reports
 */
@PleaseService
public class QuestionReportServiceImpl implements QuestionReportService {

    private static final Logger LOG = LoggerFactory.getLogger(QuestionReportServiceImpl.class);

    @PleaseInject
    private QuestionService questionService;

    @Override
    public void createQuestionReport(OutputStream outputStream) throws ServiceException {
        LOG.debug("Creating question pdf report");
        Document document = new Document(PageSize.A4.rotate());
        List<QuestionDto> questions = questionService.findAll();
        PdfWriter.getInstance(document, outputStream);

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16);
        fontTitle.setColor(CMYKColor.DARK_GRAY);
        Paragraph paragraph = new Paragraph("List of the Questions", fontTitle);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        PdfPTable table = new PdfPTable(6);

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
        cell.setPhrase(new Phrase("type", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("content", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("description", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("test_uuid", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("test_name", headerFont));
        table.addCell(cell);

        for (QuestionDto question : questions) {
            table.addCell(question.getUuid());
            table.addCell(question.getType());
            table.addCell(question.getContent());
            table.addCell(question.getDescription());
            table.addCell(question.getTest().getUuid());
            table.addCell(question.getTest().getName());
        }
        document.add(table);
        document.close();
    }
}
