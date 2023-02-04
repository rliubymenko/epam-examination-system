package com.epam.examinationsystem.core.service.impl;

import com.epam.di.annotation.PleaseService;
import com.epam.di.annotation.PleaseValue;
import com.epam.examinationsystem.core.service.MailService;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.stream.Stream;

@PleaseService
public class MailServiceImpl implements MailService {

    private static final Logger LOG = LoggerFactory.getLogger(MailServiceImpl.class);
    private static final String WELCOME_FILE_PATH = "../../view/mail/welcome.html";
    private static final String WELCOME_SUBJECT_UA = "Успішна реєстрація";
    private static final String WELCOME_SUBJECT_US = "Successful sign-up";
    private static final String REPORT_BODY_SUBJECT_US = "Please you can find queried report in the attached file.";

    @PleaseValue("mail.from")
    private String fromEmail;

    @PleaseValue("mail.password")
    private String password;

    @PleaseValue("mail.smtp.host")
    private String host;

    @PleaseValue("mail.smtp.auth")
    private String isAllowedAuth;

    @PleaseValue("mail.smtp.port")
    private String port;

    @PleaseValue("mail.smtp.starttls.enable")
    private String isStarttlsEnabled;

    @Override
    public void sendWelcomeMail(String toEmail, String language, String userFullName) {
        String welcomeTemplateContent = readFromFile(WELCOME_FILE_PATH);
        if (language != null && language.equals("uk_UA")) {
            welcomeTemplateContent = fillOutWelcomeTemplate(welcomeTemplateContent, new Locale("uk", "UA"), userFullName);
            sendEmail(toEmail, WELCOME_SUBJECT_UA, welcomeTemplateContent);
        } else {
            welcomeTemplateContent = fillOutWelcomeTemplate(welcomeTemplateContent, Locale.US, userFullName);
            sendEmail(toEmail, WELCOME_SUBJECT_US, welcomeTemplateContent);
        }
    }

    @Override
    public void sendMailWithAttachment(String toEmail, String reportName, String fileName, OutputStream outputStream) {
        Properties properties = getSmtpProperties();
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getDefaultInstance(properties, auth);
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(reportName);
            ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) outputStream;
            DataSource attachment = new ByteArrayDataSource(byteArrayOutputStream.toByteArray(), "application/octet-stream");
            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart attachmentPart = new MimeBodyPart();
            MimeBodyPart maiText = new MimeBodyPart();
            maiText.setContent(REPORT_BODY_SUBJECT_US, "text/plain; charset=UTF-8");
            attachmentPart.setFileName(fileName);
            attachmentPart.setDataHandler(new DataHandler(attachment));
            multipart.addBodyPart(maiText);
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException e) {
            LOG.error("Error has been occurred during sending report email to {}", toEmail);
        }
    }

    private void sendEmail(String toEmail, String subject, String content) {
        Properties properties = getSmtpProperties();
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getDefaultInstance(properties, auth);
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setContent(content, "text/html; charset=UTF-8");
            Transport.send(message);
        } catch (MessagingException e) {
            LOG.error("Error has been occurred during sending welcome email to {}", toEmail);
        }
    }

    private String readFromFile(String path) {
        StringBuilder buffer = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(this.getClass().getClassLoader().getResource(path).toURI()), StandardCharsets.UTF_8)) {
            stream.forEach(s -> buffer.append(s).append("\n"));
        } catch (IOException | URISyntaxException e) {
            LOG.debug("Error during email html template has been occurred", e);
        }
        return buffer.toString();
    }

    private Properties getSmtpProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", isAllowedAuth);
        properties.put("mail.smtp.starttls.enable", isStarttlsEnabled);
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        return properties;
    }

    private String fillOutWelcomeTemplate(String welcomeTemplate, Locale locale, String fullName) {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.bundle", locale);
        String helloMessage = bundle.getString("welcome.helloMessage");
        String welcomeMessage = bundle.getString("welcome.welcomeMessage");
        String mainMessage = bundle.getString("welcome.mainMessage");
        String signOff = bundle.getString("welcome.signOff");
        String teamSignOff = bundle.getString("welcome.teamSignOff");

        return welcomeTemplate
                .replace("{welcomeMessage}", welcomeMessage)
                .replace("{helloMessage}", helloMessage)
                .replace("{fullName}", fullName)
                .replace("{mainMessage}", mainMessage)
                .replace("{signOff}", signOff)
                .replace("{teamSignOff}", teamSignOff);
    }
}
