package com.epam.examinationsystem.core.report.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.AnswerDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.report.AnswerReportService;
import com.epam.examinationsystem.core.service.AnswerService;
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
 * The class for generating answer reports
 */
@PleaseService
public class AnswerReportServiceImpl implements AnswerReportService {

    private static final Logger LOG = LoggerFactory.getLogger(AnswerReportServiceImpl.class);

    @PleaseInject
    private AnswerService answerService;

    @Override
    public void createAnswerReport(OutputStream outputStream) throws ServiceException {
        LOG.debug("Creating answer pdf report");
        Document document = new Document(PageSize.A4.rotate());
        List<AnswerDto> answers = answerService.findAll();
        PdfWriter.getInstance(document, outputStream);

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16);
        fontTitle.setColor(CMYKColor.DARK_GRAY);
        Paragraph paragraph = new Paragraph("List of the Answers", fontTitle);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        PdfPTable table = new PdfPTable(5);

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
        cell.setPhrase(new Phrase("content", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("is_correct", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("question_uuid", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("question_content", headerFont));
        table.addCell(cell);

        for (AnswerDto answer : answers) {
            table.addCell(answer.getUuid());
            table.addCell(answer.getContent());
            table.addCell(answer.getIsCorrect());
            table.addCell(answer.getQuestion().getUuid());
            table.addCell(answer.getQuestion().getContent());
        }
        document.add(table);
        document.close();
    }
}
