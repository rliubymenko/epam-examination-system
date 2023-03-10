package com.epam.examinationsystem.core.service;

import java.io.OutputStream;

public interface MailService {

    void sendNewPassword(String toEmail, String newPassword);

    void sendWelcomeMail(String toEmail, String language, String fullName);

    void sendMailWithAttachment(String toEmail, String reportName, String fileName, OutputStream outputStream);
}
