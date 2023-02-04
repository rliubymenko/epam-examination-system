package com.epam.examinationsystem.core.report;

import com.epam.examinationsystem.core.exception.ServiceException;

import java.io.OutputStream;

public interface AnswerReportService {

    void createAnswerReport(OutputStream outputStream) throws ServiceException;
}
