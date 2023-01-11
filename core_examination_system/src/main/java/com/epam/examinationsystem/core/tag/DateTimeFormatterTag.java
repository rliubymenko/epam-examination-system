package com.epam.examinationsystem.core.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class DateTimeFormatterTag extends TagSupport {

    private String datetime;

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            DateTimeFormatter pattern = DateTimeFormatter
                    .ofLocalizedDateTime(FormatStyle.MEDIUM)
                    .withLocale(Locale.getDefault());
            String formattedDate = LocalDateTime.parse(datetime).format(pattern);
            JspWriter writer = pageContext.getOut();
            writer.print(formattedDate);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
