package com.epam.examinationsystem.core.web.command.impl.admin.pdf;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.report.UserReportService;
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

@PleaseService
public class GenerateUserPdfReportCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(GenerateUserPdfReportCommand.class);

    @PleaseInject
    private UserReportService userReportService;

    @PleaseInject
    private MailService mailService;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Starting generation user pdf report");
        String type = request.getParameter(Parameter.TYPE);
        try {
            DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
            String currentDateTime = dateFormat.format(new Date());
            String fileName = "users_" + currentDateTime + ".pdf";
            if (type.equals("download")) {
                response.setContentType("application/pdf");
                String headerKey = "Content-Disposition";
                String headerValue = "attachment; filename=" + fileName;
                response.setHeader(headerKey, headerValue);
                userReportService.createUserReport(response.getOutputStream());
            } else if (type.equals("email")) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                UserDto user = (UserDto) request.getSession().getAttribute(SessionConstant.CURRENT_USER);
                userReportService.createUserReport(outputStream);
                mailService.sendMailWithAttachment(user.getEmail(), "Report on users", fileName, outputStream);
            }
            return new CommandResult(Path.USERS, true);
        } catch (ServiceException | IOException e) {
            LOG.error("Error during generation user pdf report has been occurred {}", e.getMessage());
            return new CommandResult(Path.HOME);

        }
    }
}
