package com.epam.examinationsystem.core.web.command.impl.admin.pdf;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.report.AnswerReportService;
import com.epam.examinationsystem.core.service.MailService;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Parameter;
import com.epam.examinationsystem.core.web.command.constant.Path;
import com.epam.examinationsystem.core.web.command.constant.SessionConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Extends the ActionCommand interface to provide a command to generate a pdf report for answers.
 */
@PleaseService
public class GenerateAnswerPdfReportCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(GenerateAnswerPdfReportCommand.class);

    @PleaseInject
    private AnswerReportService answerReportService;

    @PleaseInject
    private MailService mailService;

    /**
     * Returns the CommandResult instance that contains the following page and the redirect flag.
     *
     * @param request  the HttpServletRequest instance.
     * @param response the HttpServletResponse instance.
     * @return the CommandResult instance.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Starting generation answer pdf report");
        String type = request.getParameter(Parameter.TYPE);
        try {
            DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
            String currentDateTime = dateFormat.format(new Date());
            String fileName = "answers_" + currentDateTime + ".pdf";
            if (type.equals("download")) {
                response.setContentType("application/pdf");
                String headerKey = "Content-Disposition";
                String headerValue = "attachment; filename=" + fileName;
                response.setHeader(headerKey, headerValue);
                answerReportService.createAnswerReport(response.getOutputStream());
            } else if (type.equals("email")) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                UserDto user = (UserDto) request.getSession().getAttribute(SessionConstant.CURRENT_USER);
                answerReportService.createAnswerReport(outputStream);
                mailService.sendMailWithAttachment(user.getEmail(), "Report on answers", fileName, outputStream);
            }
            return new CommandResult(Path.ANSWERS, true);
        } catch (ServiceException | IOException e) {
            LOG.error("Error during generation answer pdf report has been occurred {}", e.getMessage());
            return new CommandResult(Path.HOME);
        }
    }
}
