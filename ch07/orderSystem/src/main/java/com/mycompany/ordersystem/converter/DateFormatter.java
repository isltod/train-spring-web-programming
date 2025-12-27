package com.mycompany.ordersystem.converter;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter implements Formatter<Date> {

    private String format;

    public DateFormatter(String format) {
        this.format = format;
    }

    public DateFormatter() {}

    public void setFormat(String format) {
        this.format = format;
    }

    private DateFormat getDateFormat(Locale locale) {
        if (StringUtils.hasText(this.format)) {
            return new SimpleDateFormat(this.format, locale);
        } else {
            return SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM, locale);
        }
    }
    @Override
    public Date parse(String date, Locale locale) throws ParseException {
        return getDateFormat(locale).parse(date);
    }

    @Override
    public String print(Date date, Locale locale) {
        return getDateFormat(locale).format(date);
    }
}
