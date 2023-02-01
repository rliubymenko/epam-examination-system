package com.epam.examinationsystem.core.service;

public interface MailService {

    void sendWelcomeMail(String toEmail, String language, String fullName);

    void sendMailWithAttachment();
}
